package com.almacen.module.storage.service;

import com.almacen.module.file.UserFile;
import com.almacen.module.storage.FileFolder;

import java.util.List;

public interface FileFolderService {

    void save(FileFolder fileFolder);

    void delete(FileFolder fileFolder);

    List<UserFile> findFilesInFolder(Integer folderId);
}
