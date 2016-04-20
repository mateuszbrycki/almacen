package com.almacen.module.base;

import com.almacen.module.file.service.FileService;
import com.almacen.module.folder.Folder;
import com.almacen.module.folder.exception.FolderNotFoundException;
import com.almacen.module.folder.service.FolderService;
import com.almacen.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(BaseUrls.APPLICATION)
public class ApplicationController {

    @Inject
    private FileService fileService;

    @Inject
    private FolderService folderService;

    private static final Logger logger = Logger.getLogger(ApplicationController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String listAction(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws FolderNotFoundException {

        //TODO mbrycki not services, model -> repository?
        Integer userId = UserUtils.getUserId(request, response);
        logger.debug(userId);

        Folder parent_folder = this.folderService.findFolderByPhysicalPath("uploads/" + userId);
        model.addAttribute("files", fileService.findUserFilesByUserId(userId));
        model.addAttribute("parent_folder", parent_folder);
        model.addAttribute("folders", folderService.findFoldersByUserId(userId));


        return "controller/default/logged";
    }

}
