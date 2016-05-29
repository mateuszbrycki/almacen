package com.almacen.module.storage.service;

import com.almacen.module.file.UserFile;
import com.almacen.module.storage.FileFolder;
import com.almacen.module.storage.repository.FileFolderRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<FileFolder> fileFolders = (List<FileFolder>) fileFolderRepository.findByFolderId(folderId);

        return this.mapUserFileList(fileFolders);
    }

    private List<UserFile> mapUserFileList(List<FileFolder> fileFolders) {
        List<UserFile> userFiles = new ArrayList<>();

        for(FileFolder f: fileFolders) {
            userFiles.add(f.getUserFile());
        }

        return userFiles;
    }
}
