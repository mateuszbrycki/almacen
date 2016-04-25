package com.almacen.module.file;

import com.almacen.module.base.BaseUrls;
import com.almacen.module.file.service.FileService;
import com.almacen.module.file.specification.UserFileSpecification;
import com.almacen.module.file.utils.FileUtils;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import com.almacen.specification.Specification;
import com.almacen.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

@Controller
@RequestMapping(FileUrls.FILE)
public class FileController {

    @Inject
    private FileService fileService;

    private static final Logger logger = Logger.getLogger(FileController.class);

    @Inject
    private UserService userService;

    @Inject
    private UserFileSpecification specification;

    @Inject
    private MessageSource messageSource;

    private String[] args = {};

    @RequestMapping(value = FileUrls.FILE_UPLOAD, method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("file") MultipartFile file,
                             RedirectAttributes attributes, Locale locale) throws UserNotFoundException, FileNotFoundException {

        Integer userId = UserUtils.getUserId(request, response);
        UserFile userFile = new UserFile();
        UserFile temp;
        String path = request.getContextPath();

        if (file.isEmpty()) {
            return "redirect:" + BaseUrls.APPLICATION;
        }

        if((temp = fileService.findUserFileByName(file.getOriginalFilename(), userId)) != null) {
            fileService.saveFile(temp);

            return "redirect:" + BaseUrls.APPLICATION;
        }

                String[] fileArray = file.getOriginalFilename().split("\\.");
                String extension = fileArray[fileArray.length - 1];

                userFile.setName(file.getOriginalFilename());
                userFile.setExtension(extension);
                userFile.setSize(file.getSize());
                userFile.setUser(userService.findUserById(userId));


        if (!specification.isSatisfiedBy(userFile)) {
            attributes.addFlashAttribute("error", messageSource.getMessage("file.extension.blocked", args, locale));
            return "redirect:" + BaseUrls.APPLICATION;
        }

        fileService.saveFile(userFile);

        File filePath = new File(path + FileUrls.FILE_UPLOAD + "/" + userId);

            FileUtils.saveFile(file, filePath);

        return "redirect:" + BaseUrls.APPLICATION;
    }

    @RequestMapping(value = FileUrls.Api.FILE_DELETE_ID, method = RequestMethod.DELETE)
    public ResponseEntity<List<UserFile>> deleteFile(@PathVariable("fileId") Integer fileId,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) throws FileNotFoundException {

        Integer userId = UserUtils.getUserId(request, response);
        String filename = fileService.findUserFileByFileId(fileId).getName();

        try {
            fileService.deleteFileByFileIdAndUserId(fileId, userId);

        } catch (NullPointerException ex) {
            return new ResponseEntity<>(fileService.findUserFilesByUserId(userId), HttpStatus.FORBIDDEN);
        }

        File filePath = new File(request.getContextPath() + FileUrls.FILE_UPLOAD + "/" + userId + "/" + filename);

            if(!FileUtils.deleteFile(filePath)) {

                return new ResponseEntity<>(fileService.findUserFilesByUserId(userId), HttpStatus.FORBIDDEN);
            }

        return new ResponseEntity<>(fileService.findUserFilesByUserId(userId), HttpStatus.OK);
    }
}
