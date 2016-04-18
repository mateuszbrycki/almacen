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
import org.springframework.web.bind.annotation.*;
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

            UserFile userFile = new UserFile();



            if(!file.isEmpty()) {
                StringTokenizer stringTokenizer = new StringTokenizer(file.getOriginalFilename(), ".");

                    /*stringTokenizer.

                System.out.println(fileArray[1]);
                logger.debug(fileArray.toString());*/

                String extension = "a";

                userFile.setName(file.getOriginalFilename());
                userFile.setExtension(extension);
                userFile.setSize(file.getSize());
            } else {
                return "redirect:" + BaseUrls.APPLICATION;
            }

            if(specification.isSatisfiedBy(userFile)) {
                userFile.setUser(userService.findUserById(UserUtils.getUserId(request, response)));
            } else {
                attributes.addFlashAttribute("error", messageSource.getMessage("file.extension.blocked", args, locale));

                return "redirect:bad_file";
            }


        fileService.saveFile(userFile);

        String path = request.getContextPath();

        logger.debug(path);

        File filePath = new File(path + FileUrls.FILE_UPLOAD + "/" + UserUtils.getUserId(request,response));
        filePath.setWritable(true);

            if(!filePath.exists()) {
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
