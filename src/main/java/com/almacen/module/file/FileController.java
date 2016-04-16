package com.almacen.module.file;

import com.almacen.module.file.service.FileService;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import com.almacen.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping(FileUrls.FILE)
public class FileController {

    @Inject
    private FileService fileService;

    @Inject
    private Logger logger;

    @Inject
    private UserService userService;

    @RequestMapping(value = {FileUrls.FILE_UPLOAD, "/"}, method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("file") MultipartFile file) {

            UserFile userFile = new UserFile();

                userFile.setName(file.getName());
                userFile.setExtension(file.getContentType());
                userFile.setSize(file.getSize());
        try {
            userFile.setUser(userService.findUserById(UserUtils.getUserId(request,response)));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        fileService.saveFile(userFile);

        String path = request.getContextPath();

        logger.debug(path);

        File filePath = new File(path + FileUrls.FILE_UPLOAD + "/" + UserUtils.getUserId(request,response) + "/");

        try {

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            FileCopyUtils.copy(file.getInputStream(), bufferedOutputStream);
            bufferedOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:";
    }
}
