package com.almacen.module.storage.service;

import com.almacen.module.file.UserFile;
import com.almacen.module.storage.FileFolder;
import com.almacen.module.storage.repository.FileFolderRepository;

import javax.annotation.Resource;
import java.util.List;

public class FileFolderServiceImpl implements FileFolderService {

    @Resource
    private FileFolderRepository fileFolderRepository;

    @Override
    public void save(FileFolder fileFolder) {
        fileFolderRepository.save(fileFolder);
    }

    @Override
    public void delete(FileFolder fileFolder) {
        fileFolderRepository.save(fileFolder);
    }

    @Override
    public List<UserFile> findFilesInFolder(Integer folderId) {
        return fileFolderRepository.findUserFiles(folderId);
    }
}
