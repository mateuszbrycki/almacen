package com.almacen.module.file.service;


import com.almacen.module.file.UserFile;
import com.almacen.module.file.repository.FileRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Inject
    private FileRepository fileRepository;

    @Override
    public void saveFile(UserFile userFile) {
        fileRepository.save(userFile);
    }

    @Override
    public void updateFile(UserFile userFile) {

    }

    @Override
    public void deleteFileById(Integer fileId) {
        fileRepository.delete(fileId);
    }

    @Override
    public List<UserFile> findUserFilesByUserId(Integer userId) {
        return null; //fileRepository.findUserFilesByUserId(userId);
    }

    @Override
    public Long checkUserFileSize(UserFile userFile) {
        return userFile.getSize();
    }

    @Override
    public void setMaximumSize(Long size) {

    }

    @Override
    public void getMaximumSize() {
    }
}
