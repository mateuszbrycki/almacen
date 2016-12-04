package com.almacen.module.base;

import com.almacen.module.file.service.FileService;
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
import java.util.List;

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
        String userPath = this.folderCreationPolicy.generateFolderPath(userId);

        Folder defaultFolder = folderService.findUserDefaultFolder(userId);

        if (defaultFolder == null) {
            String path = request.getContextPath();

            Folder folder = new Folder();
            folder.setUser(user);
            folder.setPhysicalPath(path + userPath);
            folder.setFolderName(userId.toString());
            folder.setIsDefaultFolder(true);
            this.folderService.saveFolder(folder);
            System.out.println(request.getContextPath());
            File file = new File(request.getContextPath() + userPath);
            file.mkdirs();

            defaultFolder = folderService.findUserDefaultFolder(userId);
        }

        List<Folder> folders = folderService.findFoldersFromUserDefaultFolder(defaultFolder.getId());

        if(folders != null)
            model.addAttribute("folders", folders);

        model.addAttribute("parentFolder", defaultFolder);
        model.addAttribute("files", fileService.findUserFilesByFolderId(defaultFolder.getId()));

        return "controller/default/logged";
    }

}
