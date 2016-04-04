package com.almacen.module.folder;

import com.almacen.module.user.User;
import com.almacen.module.user.UserUrls;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import com.almacen.util.UserUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Locale;

/**
 * Created by Magic on 04.04.2016.
 */
@Controller
@RequestMapping(UserUrls.USER)
public class FolderPolicy {

    @Inject
    private UserService userService;

    @Inject
    private MessageSource messageSource;

    private String[] args = {};

    private static final String UPLOAD_PATH = "uploads";
    private String viewPath = "controller/folder/";


    @RequestMapping(value = UserUrls.FOLDER_CREATE, method = RequestMethod.POST)
    public String saveUploadedFiles(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestParam("folder") String folder,
                                     RedirectAttributes attributes, Locale locale) throws UserNotFoundException {


        Integer userId = UserUtils.getUserId(request, response);
            User user = this.userService.findUserById(userId);
            String uploadPath = FolderPolicy.UPLOAD_PATH + "/" + user.getUsername()+ "/" + folder;
            String path = request.getContextPath();
            System.out.println(path);
            File file = new File(path+"/"+uploadPath);
            System.out.println(file);
            if (!file.exists()) {
                file.mkdirs();
                attributes.addFlashAttribute("success", messageSource.getMessage("folder.message.success.create", args, locale));
            }
            else
                attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.create", args, locale));

        return "redirect:" + UserUrls.FOLDER_CREATE_FULL;
    }

    @RequestMapping(value = UserUrls.FOLDER_CREATE_VIEW, method = RequestMethod.GET)
    public String createFolderPage(HttpServletRequest request, HttpServletResponse response) {

        return this.viewPath + "main";
    }

//    @RequestMapping(value = UserUrls.FOLDER_SHOW, method = RequestMethod.GET)
//    public String showFolderPage(HttpServletRequest request, HttpServletResponse response) {
//
//        return this.viewPath + "listFiles";
//    }

    @RequestMapping(value = UserUrls.FOLDER_SHOW, method = RequestMethod.GET)
    public String listFilesAndFolders(HttpServletRequest request, HttpServletResponse response) throws UserNotFoundException {

        Integer userId = UserUtils.getUserId(request, response);
        User user = this.userService.findUserById(userId);
        String uploadPath = FolderPolicy.UPLOAD_PATH + "/" + user.getUsername();
        String path = request.getContextPath();
        System.out.println(path);
        File directory = new File(path+"/"+uploadPath);
        System.out.println(directory);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            System.out.println(file.getName());
        }
        return this.viewPath + "listFiles";
    }
}
