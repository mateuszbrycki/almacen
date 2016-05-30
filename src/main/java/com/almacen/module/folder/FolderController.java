package com.almacen.module.folder;

import com.almacen.module.base.BaseUrls;
import com.almacen.module.folder.dto.FolderDTO;
import com.almacen.module.folder.exception.FolderNotFoundException;
import com.almacen.module.folder.policy.FolderCreationPolicy;
import com.almacen.module.folder.service.FolderService;
import com.almacen.module.folder.specification.FolderSpecification;
import com.almacen.module.folder.utils.FolderUtils;
import com.almacen.module.mail.Mail;
import com.almacen.module.mail.service.MailService;
import com.almacen.module.share.service.ShareService;
import com.almacen.module.storage.StorageUrls;
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
    private FolderCreationPolicy folderCreationPolicy;

    @Inject
    private FolderSpecification specification;

    @Inject
    private MessageSource messageSource;

    @Inject
    private FolderUtils folderUtils;

    @Inject
    private MailService mailService;

    @Inject
    private UserService userService;

    @Inject
    private ShareService shareService;

    private String[] args = {};

    private String viewPath = "controller/folder/";


    @RequestMapping(value = FolderUrls.FOLDER_DELETE, method = RequestMethod.POST)
    public String deleteFolder(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam("folder_id") Integer folderId,
                               RedirectAttributes attributes, Locale locale) throws UserNotFoundException, FolderNotFoundException, IOException {

        Folder folder = folderService.findFolderById(folderId);
        String path = this.folderService.getPhysicalPathByFolderId(folderId);
        String deletePath = request.getContextPath() + this.folderCreationPolicy.generateFolderEditablePath(path);
        File dir = new File(deletePath);
        this.folderService.deleteFolderById(folderId);
        if (this.folderUtils.folderDelete(dir))
            attributes.addFlashAttribute("success", messageSource.getMessage("folder.message.success.delete", args, locale));
        else
            attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.delete", args, locale));

        return "redirect:" + StorageUrls.Api.FOLDER_CONTENT + "/" + folder.getParentFolderId();
    }

    @RequestMapping(value = FolderUrls.FOLDER_EDIT, method = RequestMethod.POST)
    public String editFolder(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam("folder_name") String folderName,
                             @RequestParam("folder_id") Integer folderId,
                             RedirectAttributes attributes, Locale locale) throws UserNotFoundException, FolderNotFoundException, IOException {

        Folder folder = new Folder();
        folder.setFolderName(folderName);

        Folder temp = folderService.findFolderById(folderId);

        if (specification.isSatisfiedBy(folder)) {
            attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.name", args, locale));
        } else {
            Integer userId = UserUtils.getUserId(request, response);
            String path = this.folderService.getPhysicalPathByFolderId(folderId);
            String editPath = request.getContextPath() + this.folderCreationPolicy.generateFolderEditablePath(path);
            if (this.folderUtils.changeFolderName(path, editPath, folderName, folderId, userId))
                attributes.addFlashAttribute("success", messageSource.getMessage("folder.message.success.edit", args, locale));
            else
                attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.edit", args, locale));
        }

        return "redirect:" + StorageUrls.Api.FOLDER_CONTENT + "/" + temp.getParentFolderId();
    }

    @RequestMapping(value = FolderUrls.FOLDER_CREATE, method = RequestMethod.POST)
    public String createFolder(@ModelAttribute @Valid FolderDTO folderDTO,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestParam("folder_name") String folderName,
                               @RequestParam("folder_id") Integer folderId,
                               RedirectAttributes attributes, Locale locale) throws UserNotFoundException, FolderNotFoundException {

        Folder folder = new Folder();
        folder.setFolderName(folderName);

        if (specification.isSatisfiedBy(folder)) {
            attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.name", args, locale));
        } else {
            Integer userId = UserUtils.getUserId(request, response);

            String path = request.getContextPath();
            folder = this.folderUtils.createPath(userId, folderId, folderDTO);
            folder.setIsDefaultFolder(false);

            String defaultPath = folder.getPhysicalPath();

            if (!this.folderService.checkIfFolderWithNameExists(defaultPath, folderName)) {

                this.folderUtils.saveFolder(defaultPath, folder, path);
                attributes.addFlashAttribute("success", messageSource.getMessage("folder.message.success.create", args, locale));

            } else
                attributes.addFlashAttribute("error", messageSource.getMessage("folder.message.error.create", args, locale));

        }

        return "redirect:" + StorageUrls.Api.FOLDER_CONTENT + "/" + folderId;
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

    @RequestMapping(value = FolderUrls.FOLDER_SHARE, method = RequestMethod.POST)
    public String shareFolder(HttpServletRequest request,
                              HttpServletResponse response,
                              @RequestParam("share_email") String shareEmail,
                              @RequestParam("folder_id") Integer folderId,
                              RedirectAttributes attributes, Locale locale) throws FolderNotFoundException, UserNotFoundException {

        Integer userId = UserUtils.getUserId(request, response);
        Mail mail = mailService.createBody(userId, folderId, request);
        boolean mailSent = mailService.send(shareEmail, mail.getSubject(), mail.getBody());
        if(mailSent)
            attributes.addFlashAttribute("success", messageSource.getMessage("share.send.success", args, locale));
        else
            attributes.addFlashAttribute("error", messageSource.getMessage("share.send.error", args, locale));
        return "redirect:" + BaseUrls.APPLICATION;
    }

    @RequestMapping(value = FolderUrls.FOLDER_SHARE_RESOLVER, method = RequestMethod.GET)
    public String shareFolderResolver(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @PathVariable("folderHash") String folderHash) throws FolderNotFoundException {

        Integer hashEncode = Integer.parseInt(shareService.decode(folderHash));
        return "redirect:" + StorageUrls.Api.FOLDER_CONTENT + "/" + hashEncode;
    }

    }
