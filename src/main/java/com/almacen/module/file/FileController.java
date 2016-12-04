package com.almacen.module.file;

import com.almacen.module.base.BaseUrls;
import com.almacen.module.file.service.FileService;
import com.almacen.module.file.specification.UserFileSpecification;
import com.almacen.module.file.utils.FileUtils;
import com.almacen.module.folder.Folder;
import com.almacen.module.folder.exception.FolderNotFoundException;
import com.almacen.module.folder.service.FolderService;
//import com.almacen.module.storage.FileFolder;
//import com.almacen.module.storage.service.FileFolderService;
import com.almacen.module.storage.FileFolder;
import com.almacen.module.storage.FileFolderKey;
import com.almacen.module.storage.StorageUrls;
import com.almacen.module.storage.service.FileFolderService;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import com.almacen.util.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(FileUrls.FILE)
public class FileController {

    @Inject
    private FileService fileService;

    private static final Logger logger = Logger.getLogger(FileController.class);

    @Inject
    private UserService userService;

    @Inject
    private UserFileSpecification specification;

    @Inject
    private MessageSource messageSource;

    @Inject
    private FolderService folderService;

    @Inject
    private FileFolderService fileFolderService;

    private FileUtils fileUtils = new FileUtils();

    private String[] args = {};

    @RequestMapping(value = FileUrls.FILE_UPLOAD, method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam("folderId") Integer folderId,
                             @RequestParam("file") MultipartFile file,
                             ModelMap modelMap,
                             RedirectAttributes attributes, Locale locale)
            throws UserNotFoundException, FileNotFoundException,
            UnsupportedEncodingException, FolderNotFoundException {

        Folder folder = this.folderService.findFolderById(folderId);
        Integer userId = UserUtils.getUserId(request, response);
        UserFile temp;
        File filePath = fileUtils.createFolderPath(request.getContextPath(), folder.getPhysicalPath());

        if (file.isEmpty())
            return this.getRedirectPath(folderId);

        if ((temp = fileService.findFileInFolder(file.getOriginalFilename(), folderId)) != null) {

            temp.setSize(file.getSize());
            fileService.saveFile(temp);
            fileUtils.saveFile(file, filePath);

            return this.getRedirectPath(folderId);
        }

        UserFile userFile = fileUtils.mapUserFileObject(file, userService.findUserById(userId));

        if (!specification.isSatisfiedBy(userFile)) {
            attributes.addFlashAttribute("error", messageSource.getMessage("file.extension.blocked", args, locale));
            return this.getRedirectPath(folderId);
        }

        fileService.saveFile(userFile);
        List<UserFile> files = this.fileService.findUserFilesByFolderId(folderId);
        files.add(userFile);

        this.saveFileFolderEntity(userFile.getFileId(), folderId);

        fileUtils.saveFile(file, filePath);
        this.folderService.updateFolder(folder);

        modelMap.addAttribute("parentFolder", this.folderService.findFolderById(folderId));
        modelMap.addAttribute("files", files);
        modelMap.addAttribute("folders", folderService.findFoldersByParentFolderId(folderId));

        return this.getRedirectPath(folderId);
    }

    @RequestMapping(value = FileUrls.Api.FILE_DELETE_ID, method = RequestMethod.DELETE)
    public ResponseEntity<List<UserFile>> deleteFile(@PathVariable("fileId") Integer fileId,
                                                     @PathVariable("folderId") Integer folderId,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response) throws FileNotFoundException, FolderNotFoundException {

        Integer userId = UserUtils.getUserId(request, response);
        String filename = fileService.findUserFileByFileId(fileId).getName();
        Folder folder = this.folderService.findFolderById(folderId);
        File filePath = fileUtils.createFilePath(request.getContextPath(), folder.getPhysicalPath(), filename);

        fileService.deleteFileByFileIdAndUserId(fileId, userId);

        if (!fileUtils.deleteFile(filePath))
            return new ResponseEntity<>(fileService.findUserFilesByUserId(userId), HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(fileService.findUserFilesByUserId(userId), HttpStatus.OK);
    }

    @RequestMapping(value = FileUrls.Api.FILE_DOWNLOAD_ID, method = RequestMethod.GET)
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("fileId") Integer fileId,
                             @PathVariable("folderId") Integer folderId) throws IOException, FolderNotFoundException {

        String filename = fileService.findUserFileByFileId(fileId).getName();
        Folder folder = this.folderService.findFolderById(folderId);
        File filePath = fileUtils.createFilePath(request.getContextPath(), folder.getPhysicalPath(), filename);

        if (!filePath.exists())
            return;

        InputStream inputStream = new FileInputStream(filePath);

        response.setContentType(fileUtils.getFileMimeType(filename));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        response.setCharacterEncoding("UTF-8");

        FileCopyUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

    private void saveFileFolderEntity(Integer fileId, Integer folderId) {

        FileFolderKey fileFolderKey = new FileFolderKey();
        fileFolderKey.setFolder(folderId);
        fileFolderKey.setUserFile(fileId);

        FileFolder fileFolder = new FileFolder();
        fileFolder.setFileFolderKey(fileFolderKey);

        fileFolderService.save(fileFolder);
    }

    private String getRedirectPath(Integer folderId) {
        return "redirect:" + StorageUrls.Api.FOLDER_CONTENT + "/" + folderId;
    }
}
