package com.almacen.module.storage.service;

import com.almacen.module.storage.FileFolder;
import com.almacen.module.storage.repository.FileFolderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("fileFolderService")
@Transactional
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
}
