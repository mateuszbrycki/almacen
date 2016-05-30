package com.almacen.module.file.service;

import com.almacen.module.file.UserFile;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public interface FileService {

    void saveFile(UserFile userFile);

    void deleteFileById(Integer fileId);

    List<UserFile> findUserFilesByUserId(Integer userId);

    List<UserFile> findUserFilesByFolderId(Integer folderId);

    UserFile findUserFileByName(String name, Integer userId);

    void deleteFileByFileIdAndUserId(Integer fileId, Integer userId);

    UserFile findUserFileByFileId(Integer fileId) throws FileNotFoundException;

    Long getWholeSizeUserFiles(Integer userId);

    List<String> getUserFilesAllExtension(Integer userId);

    HashMap<String, Integer> getUserFilesAllQuantity(Integer userId);
}
