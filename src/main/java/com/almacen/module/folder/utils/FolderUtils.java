package com.almacen.module.folder.utils;


import com.almacen.module.folder.Folder;
import com.almacen.module.folder.dto.FolderDTO;
import com.almacen.module.folder.exception.FolderNotFoundException;
import com.almacen.module.folder.factory.FolderFactory;
import com.almacen.module.folder.service.FolderService;
import com.almacen.module.user.User;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.service.UserService;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

@Component("folderUtils")
public class FolderUtils {

    @Inject
    FolderService folderService;

    @Inject
    UserService userService;

    @Inject
    FolderFactory folderFactory;

    public Folder createPath(Integer userId, Integer folderId, FolderDTO folderDTO) throws UserNotFoundException, FolderNotFoundException {
        String defaultPath;
        User user = this.userService.findUserById(userId);
        String parentFolderName = this.folderService.getFolderNameByFolderId(folderId);

        String physicalPath = folderDTO.getPhysical_path();
        Folder folder = folderFactory.createFromDTO(folderDTO);
        folder.setUser(user);

        if (!this.folderService.checkIfParentIdExists(userId, folderId)) {

            defaultPath = physicalPath + "/";
            folder.setPhysicalPath(defaultPath);
            folder.setParentFolderId(folderId);

        } else {

            defaultPath = physicalPath + parentFolderName + "/";
            folder.setPhysicalPath(defaultPath);
            folder.setParentFolderId(folderId);

        }
        return folder;
    }

    public Boolean saveFolder(String uploadPath, Folder folder, String path) {
        File file = new File(path + "/" + uploadPath);
        file.mkdirs();
        this.folderService.saveFolder(folder);
        return true;
    }

    public boolean folderDelete(File file) {
        if (!file.exists())
            return false;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                folderDelete(f);
            }
        }
        file.delete();
        return true;
    }

    public void changeFolderPath(Integer userId, Integer folderId, String newPath) throws FolderNotFoundException {

        String physicalPath = this.folderService.getPhysicalPathByFolderId(folderId)
                                + this.folderService.getFolderNameByFolderId(folderId);
        List<Folder> folders = this.folderService.findFoldersByUserId(userId);

        for (Folder folder : folders) {

            String tempPath = folder.getPhysicalPath();

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
}
