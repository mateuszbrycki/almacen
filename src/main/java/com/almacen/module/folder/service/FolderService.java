package com.almacen.module.folder.service;

import com.almacen.module.folder.Folder;
import com.almacen.module.folder.exception.FolderNotFoundException;

import java.util.List;


public interface FolderService {

    void saveFolder(Folder folder);

    void updateFolder(Folder folder);

    void deleteFolderById(Integer id);

    List<Folder> findFoldersByUserId(Integer userId);

    Boolean checkIfParentIdExists(Integer userId, Integer folderId);

    Boolean checkIfFolderWithNameExists(String physical_path, String folder_name);

    Integer getFolderIdByPhysicalPath(String physical_path) throws FolderNotFoundException;

    String getPhysicalPathByFolderId(Integer folder_id) throws FolderNotFoundException;

    String getFolderNameByFolderId(Integer folder_id) throws FolderNotFoundException;

    Folder findFolderById(Integer folder_id) throws FolderNotFoundException;

    Folder findFolderByPhysicalPath(String physical_path) throws FolderNotFoundException;

    List<Folder> findFoldersByPhysicalPath(String physical_path) throws FolderNotFoundException;

}
