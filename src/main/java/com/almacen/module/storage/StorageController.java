package com.almacen.module.storage;

import com.almacen.module.base.BaseUrls;
import com.almacen.module.file.service.FileService;
import com.almacen.module.folder.service.FolderService;
import com.almacen.module.user.service.UserService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping
public class StorageController {

    @Inject
    private FileService fileService;

    @Inject
    private FolderService folderService;

    @Inject
    private UserService userService;

    @RequestMapping
    public String listContentInFolder(ModelMap model,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {

        return "redirect:" + BaseUrls.APPLICATION;
    }
}
