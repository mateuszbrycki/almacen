package com.almacen.module.folder;

import com.almacen.logger.LoggerMessage;
import com.almacen.module.admin.AdminUrls;
import com.almacen.module.base.BaseController;
import com.almacen.module.folder.dto.FolderDTO;
import com.almacen.module.folder.exception.FolderNotFoundException;
import com.almacen.module.folder.service.FolderService;
import com.almacen.module.user.User;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import com.almacen.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping(FolderUrls.FOLDER)
public class FolderController {

    @Inject
    private FolderAbstractFactory folderFactory;

    @Inject
    private FolderService folderService;

    @Inject
    private UserService userService;

    @Inject
    private MessageSource messageSource;

    private String[] args = {};

    private static final String UPLOAD_PATH = "uploads";
    private String viewPath = "controller/folder/";


    @RequestMapping(value = FolderUrls.FOLDER_CREATE, method = RequestMethod.POST)
    public String createFolder(@ModelAttribute @Valid FolderDTO folderDTO,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam("folder_name") String folder_name,
                               @RequestParam("folder_id") Integer folder_id,
                               RedirectAttributes attributes, Locale locale) throws UserNotFoundException, FolderNotFoundException {

        String upload_path;
        String default_path;
        Integer userId = UserUtils.getUserId(request, response);
        User user = this.userService.findUserById(userId);
        String parent_folder_name = this.folderService.getFolderNameByFolderId(folder_id);

        String physical_path = folderDTO.getPhysical_path();
        String path = request.getContextPath();
        Folder folder = folderFactory.createFromDTO(folderDTO);
        folder.setUser(user);

        if (!this.folderService.checkIfParentIdExists(userId, folder_id)) {
            default_path = physical_path + "/";
            upload_path = default_path + folder_name;
            folder.setPhysical_path(default_path);
            folder.setParent_folder_id(folder_id);
        } else {
            default_path = physical_path + parent_folder_name + "/";
            upload_path = default_path + folder_name;
            folder.setPhysical_path(default_path);
            folder.setParent_folder_id(folder_id);
        }

        if (!this.folderService.checkIfFolderWithNameExists(default_path, folder_name)) {
            File file = new File(path + "/" + upload_path);
            file.mkdirs();
            this.folderService.saveFolder(folder);
            attributes.addFlashAttribute("success", messageSource.getMessage("folder.message.success.create", args, locale));
        } else
            attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.create", args, locale));


        return "redirect:" + FolderUrls.FOLDER_SHOW_FULL;
    }

    @RequestMapping(value = FolderUrls.FOLDER_SHOW, method = RequestMethod.GET)
    public String listFolders(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws UserNotFoundException, FolderNotFoundException {
        Integer userId = UserUtils.getUserId(request, response);
        User user = this.userService.findUserById(userId);
        String user_path = FolderController.UPLOAD_PATH + "/" + user.getUsername();

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

        String physical_path = user_path + "/";
        List<Folder> folders = this.folderService.findFoldersByPhysicalPath(physical_path);
        Folder parent_folder = this.folderService.findFolderByPhysicalPath(user_path);
        model.addAttribute("folders", folders);
        model.addAttribute("parent_folder", parent_folder);

        return this.viewPath + "listFiles";
    }

    @RequestMapping(value = FolderUrls.FOLDER_SHOW_FOLDER, method = RequestMethod.GET)
    public String listFoldersInFolders(
            @PathVariable("folderId") Integer folderId,
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap model) throws UserNotFoundException, FolderNotFoundException {

        String folder_name = this.folderService.getFolderNameByFolderId(folderId);
        String physical_path = this.folderService.getPhysicalPathByFolderId(folderId);
        String full_path = physical_path + folder_name + "/";
        List<Folder> folders = this.folderService.findFoldersByPhysicalPath(full_path);
        Folder parent_folder = this.folderService.findFolderById(folderId);
        model.addAttribute("folders", folders);
        model.addAttribute("parent_folder", parent_folder);

        return this.viewPath + "listFiles";
    }
}
