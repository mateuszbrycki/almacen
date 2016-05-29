package com.almacen.module.storage;

import com.almacen.module.base.BaseUrls;
import com.almacen.module.file.service.FileService;
import com.almacen.module.folder.exception.FolderNotFoundException;
import com.almacen.module.folder.service.FolderService;
import com.almacen.module.storage.service.FileFolderService;
import com.almacen.module.user.service.UserService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Inject
    private FileFolderService fileFolderService;

    @RequestMapping
    public String listContentInFolder(ModelMap modelMap,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      @PathVariable("folderId") Integer folderId) throws FolderNotFoundException {

        modelMap.addAttribute("parentFolder", this.folderService.findFolderById(folderId));
        modelMap.addAttribute("files", this.fileFolderService.findFilesInFolder(folderId));
        modelMap.addAttribute("folders");

        return "redirect:" + BaseUrls.APPLICATION;
    }
}
