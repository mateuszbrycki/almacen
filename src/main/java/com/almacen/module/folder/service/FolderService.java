package com.almacen.module.folder.service;

import com.almacen.module.folder.Folder;
import com.almacen.module.folder.exception.FolderNotFoundException;

import java.util.List;


public interface FolderService {

    void saveFolder(Folder folder);

    void updateFolder(Folder folder);

    void updateFolderById(Integer folderId, String folderName);

    void updateFolderPathById(String physicalPath, Integer folderId);

    void deleteFolderById(Integer id);

    List<Folder> findFoldersByUserId(Integer userId);

    Boolean checkIfParentIdExists(Integer userId, Integer folderId);

    Boolean checkIfFolderWithNameExists(String physicalPath, String folderName);

    Integer getFolderIdByPhysicalPath(String physicalPath) throws FolderNotFoundException;

    String getPhysicalPathByFolderId(Integer folderId) throws FolderNotFoundException;

    String getFolderNameByFolderId(Integer folderId) throws FolderNotFoundException;

    Folder findFolderById(Integer folderId) throws FolderNotFoundException;

    Folder findFolderByPhysicalPath(String physicalPath) throws FolderNotFoundException;

    List<Folder> findFoldersByPhysicalPath(String physicalPath) throws FolderNotFoundException;

    Folder findUserDefaultFolder(Integer userId);

    List<Folder> findFoldersFromUserDefaultFolder(Integer folderId);

    List<Folder> findFoldersByParentFolderId(Integer folderId);
}
