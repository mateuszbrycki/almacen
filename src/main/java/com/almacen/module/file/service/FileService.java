package com.almacen.module.file.service;

import com.almacen.module.file.UserFile;

import java.util.List;

public interface FileService {

    void saveFile(UserFile userFile);

    void updateFile(UserFile userFile);

    void deleteFileById(Integer fileId);

    List<UserFile> findUserFilesByUserId(Integer userId);

    UserFile findUserFileByName(String name, Integer userId);
}
