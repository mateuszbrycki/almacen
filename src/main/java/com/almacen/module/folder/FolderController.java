package com.almacen.module.folder;

import com.almacen.module.base.BaseUrls;
import com.almacen.module.folder.dto.FolderDTO;
import com.almacen.module.folder.exception.FolderNotFoundException;
import com.almacen.module.folder.policy.FolderCreationPolicy;
import com.almacen.module.folder.service.FolderService;
import com.almacen.module.folder.specification.FolderSpecification;
import com.almacen.module.folder.utils.FolderUtils;
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
import java.io.IOException;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping(FolderUrls.FOLDER)
public class FolderController {

    private static final Logger logger = Logger.getLogger(FolderController.class);

    @Inject
    private FolderAbstractFactory folderFactory;

    @Inject
    private FolderService folderService;

    @Inject
    private UserService userService;

    @Inject
    private FolderCreationPolicy folderCreationPolicy;

    @Inject
    private FolderSpecification specification;

    @Inject
    private MessageSource messageSource;

    private String[] args = {};

    private FolderUtils folderUtils = new FolderUtils();

    private String viewPath = "controller/folder/";


    public Folder createPath(Integer userId, Integer folderId, FolderDTO folderDTO) throws UserNotFoundException, FolderNotFoundException {
        String defaultPath;
        User user = this.userService.findUserById(userId);
        String parentFolderName = this.folderService.getFolderNameByFolderId(folderId);

        String physicalPath = folderDTO.getPhysical_path();
        Folder folder = folderFactory.createFromDTO(folderDTO);
        folder.setUser(user);

        if (!this.folderService.checkIfParentIdExists(userId, folderId)) {
            defaultPath = physicalPath + "/";
            folder.setPhysical_path(defaultPath);
            folder.setParent_folder_id(folderId);
        } else {
            defaultPath = physicalPath + parentFolderName + "/";
            folder.setPhysical_path(defaultPath);
            folder.setParent_folder_id(folderId);
        }
        return folder;
    }

    public Boolean saveFolder(String uploadPath, Folder folder, String path) {
        File file = new File(path + "/" + uploadPath);
        file.mkdirs();
        this.folderService.saveFolder(folder);
        return true;
    }

    public void changeFolderPath(Integer userId, Integer folderId, String newPath) throws FolderNotFoundException {
        String physicalPath = this.folderService.getPhysicalPathByFolderId(folderId) + this.folderService.getFolderNameByFolderId(folderId);
        List<Folder> folders = this.folderService.findFoldersByUserId(userId);
        for (Folder folder : folders) {
            String tempPath = folder.getPhysical_path();
            if (tempPath.contains(physicalPath)) {
                tempPath = tempPath.replace(physicalPath, newPath);
                this.folderService.updateFolderPathById(tempPath, folder.getId());
            }
        }
    }

    public boolean changeFolderName(String path, String editPath, String folderName, Integer folderId, Integer userId) throws FolderNotFoundException {
        File dir = new File(editPath);
        if (this.folderService.checkIfFolderWithNameExists(path, folderName))
            return false;
        else {
            File newDir = new File(dir.getParent() + "\\" + folderName);
            this.folderService.updateFolderById(folderId, folderName);
            changeFolderPath(userId, folderId, path + folderName);
            dir.renameTo(newDir);
            return true;
        }
    }

    @RequestMapping(value = FolderUrls.FOLDER_DELETE, method = RequestMethod.POST)
    public String deleteFolder(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam("folder_id") Integer folderId,
                               RedirectAttributes attributes, Locale locale) throws UserNotFoundException, FolderNotFoundException, IOException {

        String path = this.folderService.getPhysicalPathByFolderId(folderId);
        String folderName = this.folderService.getFolderNameByFolderId(folderId);
        String deletePath = request.getContextPath() + this.folderCreationPolicy.generateFolderEditablePath(path, folderName);
        File dir = new File(deletePath);
        this.folderService.deleteFolderById(folderId);
        if (this.folderUtils.folderDelete(dir))
            attributes.addFlashAttribute("success", messageSource.getMessage("folder.message.success.delete", args, locale));
        else
            attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.delete", args, locale));

        return "redirect:" + BaseUrls.APPLICATION;
    }

    @RequestMapping(value = FolderUrls.FOLDER_EDIT, method = RequestMethod.POST)
    public String editFolder(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam("folder_name") String folderName,
                             @RequestParam("folder_id") Integer folderId,
                             RedirectAttributes attributes, Locale locale) throws UserNotFoundException, FolderNotFoundException, IOException {

        Integer userId = UserUtils.getUserId(request, response);
        String path = this.folderService.getPhysicalPathByFolderId(folderId);
        String oldFolderName = this.folderService.getFolderNameByFolderId(folderId);
        String editPath = request.getContextPath() + this.folderCreationPolicy.generateFolderEditablePath(path, oldFolderName);
        if (changeFolderName(path, editPath, folderName, folderId, userId))
            attributes.addFlashAttribute("success", messageSource.getMessage("folder.message.success.edit", args, locale));
        else
            attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.edit", args, locale));

        return "redirect:" + BaseUrls.APPLICATION;
    }

    @RequestMapping(value = FolderUrls.FOLDER_CREATE, method = RequestMethod.POST)
    public String createFolder(@ModelAttribute @Valid FolderDTO folderDTO,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam("folder_name") String folderName,
                               @RequestParam("folder_id") Integer folderId,
                               RedirectAttributes attributes, Locale locale) throws UserNotFoundException, FolderNotFoundException {

        Folder folder = new Folder();
        folder.setFolder_name(folderName);

        if (specification.isSatisfiedBy(folder)) {
            attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.name", args, locale));
        } else {
            Integer userId = UserUtils.getUserId(request, response);

            String path = request.getContextPath();
            folder = createPath(userId, folderId, folderDTO);
            String defaultPath = folder.getPhysical_path();
            String uploadPath = defaultPath + folderName;

            if (!this.folderService.checkIfFolderWithNameExists(defaultPath, folderName)) {
                saveFolder(uploadPath, folder, path);
                attributes.addFlashAttribute("success", messageSource.getMessage("folder.message.success.create", args, locale));
            } else
                attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.create", args, locale));

        }
        return "redirect:" + BaseUrls.APPLICATION;
    }

    @RequestMapping(value = FolderUrls.FOLDER_SHOW, method = RequestMethod.GET)
    public String listFolders(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws UserNotFoundException, FolderNotFoundException {
        Integer userId = UserUtils.getUserId(request, response);
        String userPath = this.folderCreationPolicy.generateFolderPath(userId);

        String physicalPath = userPath + "/";
        List<Folder> folders = this.folderService.findFoldersByPhysicalPath(physicalPath);
        Folder parentFolder = this.folderService.findFolderByPhysicalPath(userPath);
        model.addAttribute("folders", folders);
        model.addAttribute("parent_folder", parentFolder);

        return this.viewPath + "listFiles";
    }

    @RequestMapping(value = FolderUrls.FOLDER_SHOW_FOLDER, method = RequestMethod.GET)
    public String listFoldersInFolders(
            @PathVariable("folderId") Integer folderId,
            HttpServletRequest request,
            HttpServletResponse response,
            ModelMap model) throws UserNotFoundException, FolderNotFoundException {

        String folderName = this.folderService.getFolderNameByFolderId(folderId);
        String physicalPath = this.folderService.getPhysicalPathByFolderId(folderId);
        String fullPath = physicalPath + folderName + "/";
        List<Folder> folders = this.folderService.findFoldersByPhysicalPath(fullPath);
        Folder parentFolder = this.folderService.findFolderById(folderId);
        model.addAttribute("folders", folders);
        model.addAttribute("parent_folder", parentFolder);
        return "controller/default/logged";
    }
}
