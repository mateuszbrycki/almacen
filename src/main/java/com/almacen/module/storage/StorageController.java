package com.almacen.module.storage;

import com.almacen.module.base.BaseUrls;
import com.almacen.module.file.service.FileService;
import com.almacen.module.folder.exception.FolderNotFoundException;
import com.almacen.module.folder.service.FolderService;
import com.almacen.util.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

@Controller
@RequestMapping(StorageUrls.STORAGE)
public class StorageController {

    @Inject
    private FileService fileService;

    @Inject
    private FolderService folderService;


    @RequestMapping(value = StorageUrls.Api.FOLDER_CONTENT_FULL, method = RequestMethod.GET)
    public String listContentInFolder(ModelMap modelMap,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      @PathVariable("folderId") Integer folderId) throws FolderNotFoundException, FileNotFoundException {

        System.out.println(folderId);
        modelMap.addAttribute("parentFolder", this.folderService.findFolderById(folderId));
        modelMap.addAttribute("files", fileService.findUserFilesByFolderId(folderId));
        modelMap.addAttribute("folders", folderService.findFoldersByParentFolderId(folderId));

        return "controller/default/logged";
    }
}
