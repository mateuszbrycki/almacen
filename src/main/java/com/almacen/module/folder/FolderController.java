package com.almacen.module.folder;

import com.almacen.module.base.BaseUrls;
import com.almacen.module.folder.dto.FolderDTO;
import com.almacen.module.folder.exception.FolderNotFoundException;
import com.almacen.module.folder.policy.FolderCreationPolicy;
import com.almacen.module.folder.service.FolderService;
import com.almacen.module.folder.specification.FolderSpecification;
import com.almacen.module.user.User;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import com.almacen.util.UserUtils;
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
    private FolderCreationPolicy folderCreationPolicy;

    @Inject
    private FolderSpecification specification;

    @Inject
    private MessageSource messageSource;

    private String[] args = {};

    private String viewPath = "controller/folder/";


    public Folder createPath(Integer userId, Integer folder_id, FolderDTO folderDTO) throws UserNotFoundException, FolderNotFoundException {
        String default_path;
        User user = this.userService.findUserById(userId);
        String parent_folder_name = this.folderService.getFolderNameByFolderId(folder_id);

        String physical_path = folderDTO.getPhysical_path();
        Folder folder = folderFactory.createFromDTO(folderDTO);
        folder.setUser(user);

        if (!this.folderService.checkIfParentIdExists(userId, folder_id)) {
            default_path = physical_path + "/";
            folder.setPhysical_path(default_path);
            folder.setParent_folder_id(folder_id);
        } else {
            default_path = physical_path + parent_folder_name + "/";
            folder.setPhysical_path(default_path);
            folder.setParent_folder_id(folder_id);
        }
        return folder;
    }

    public Boolean saveFolder(String upload_path, Folder folder, String path) {
        File file = new File(path + "/" + upload_path);
        file.mkdirs();
        this.folderService.saveFolder(folder);
        return true;
    }

    @RequestMapping(value = FolderUrls.FOLDER_CREATE, method = RequestMethod.POST)
    public String createFolder(@ModelAttribute @Valid FolderDTO folderDTO,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam("folder_name") String folder_name,
                               @RequestParam("folder_id") Integer folder_id,
                               RedirectAttributes attributes, Locale locale) throws UserNotFoundException, FolderNotFoundException {

        Folder folder = new Folder();
        folder.setFolder_name(folder_name);

        if (specification.isSatisfiedBy(folder)) {
            attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.name", args, locale));
        } else {
            Integer userId = UserUtils.getUserId(request, response);

            String path = request.getContextPath();
            folder = createPath(userId, folder_id, folderDTO);
            String default_path = folder.getPhysical_path();
            String upload_path = default_path + folder_name;

            if (!this.folderService.checkIfFolderWithNameExists(default_path, folder_name)) {
                saveFolder(upload_path, folder, path);
                attributes.addFlashAttribute("success", messageSource.getMessage("folder.message.success.create", args, locale));
            } else
                attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.create", args, locale));

        }
        return "redirect:" + BaseUrls.APPLICATION;
    }

    @RequestMapping(value = FolderUrls.FOLDER_SHOW, method = RequestMethod.GET)
    public String listFolders(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws UserNotFoundException, FolderNotFoundException {
        Integer userId = UserUtils.getUserId(request, response);
        String user_path = this.folderCreationPolicy.generateFolderPath(userId);

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
