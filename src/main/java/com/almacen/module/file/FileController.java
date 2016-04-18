package com.almacen.module.file;

import com.almacen.module.base.BaseUrls;
import com.almacen.module.file.service.FileService;
import com.almacen.module.file.specification.UserFileSpecification;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import com.almacen.specification.Specification;
import com.almacen.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
                             RedirectAttributes attributes, Locale locale) throws UserNotFoundException {

        Integer userId = UserUtils.getUserId(request, response);
        UserFile userFile = new UserFile();
        UserFile temp;

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
            return "bad_file";
        }


        fileService.saveFile(userFile);

        String path = request.getContextPath();

        logger.debug(path);

        File filePath = new File(path + FileUrls.FILE_UPLOAD + "/" + UserUtils.getUserId(request, response));
        filePath.setWritable(true);

        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        try {

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath + "/" + file.getOriginalFilename()));
            FileCopyUtils.copy(file.getInputStream(), bufferedOutputStream);
            bufferedOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:" + BaseUrls.APPLICATION;
    }
}
