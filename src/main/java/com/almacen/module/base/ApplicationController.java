package com.almacen.module.base;

import com.almacen.module.file.service.FileService;
import com.almacen.module.folder.service.FolderService;
import com.almacen.module.folder.Folder;
import com.almacen.module.folder.exception.FolderNotFoundException;
import com.almacen.module.folder.policy.FolderCreationPolicy;
import com.almacen.module.folder.service.FolderService;
import com.almacen.module.user.User;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import com.almacen.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Controller
@RequestMapping(BaseUrls.APPLICATION)
public class ApplicationController {

    @Inject
    private FileService fileService;

    @Inject
    private FolderService folderService;

    @Inject
    private UserService userService;

    @Inject
    private FolderCreationPolicy folderCreationPolicy;

    private static final Logger logger = Logger.getLogger(ApplicationController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String listAction(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws FolderNotFoundException, UserNotFoundException {

        //TODO mbrycki not services, model -> repository?
        Integer userId = UserUtils.getUserId(request, response);
        logger.debug(userId);

        User user = this.userService.findUserById(userId);
        String user_path = this.folderCreationPolicy.generateFolderPath(userId);

        if (!this.folderService.checkIfFolderWithNameExists(user_path, "0")) {
            String path = request.getContextPath();
            Folder mFolder = new Folder();
            mFolder.setUser(user);
            mFolder.setPhysical_path(user_path);
            mFolder.setParent_folder_id(0);
            mFolder.setFolder_name("0");
            this.folderService.saveFolder(mFolder);
            File file = new File(path + "/" + user_path);
            file.mkdirs();
        }

        Folder parent_folder = this.folderService.findFolderByPhysicalPath("uploads/" + userId);
        model.addAttribute("files", fileService.findUserFilesByUserId(userId));
        model.addAttribute("parent_folder", parent_folder);
        model.addAttribute("folders", folderService.findFoldersByUserId(userId));


        return "controller/default/logged";
    }

}
