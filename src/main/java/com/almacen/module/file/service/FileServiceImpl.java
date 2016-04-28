package com.almacen.module.file.service;


import com.almacen.module.file.UserFile;
import com.almacen.module.file.repository.FileRepository;
import com.almacen.module.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.List;

@Service("fileService")
@Transactional
public class FileServiceImpl implements FileService {

    @Resource
    private FileRepository fileRepository;

    @Override
    public void saveFile(UserFile userFile) {
        fileRepository.save(userFile);
    }

    @Override
    public void deleteFileById(Integer fileId) {
        fileRepository.delete(fileId);
    }

    @Override
    public List<UserFile> findUserFilesByUserId(Integer userId) {
        return fileRepository.findByUserId(userId);
    }

    @Override
    public UserFile findUserFileByName(String name, Integer userId) {

        return fileRepository.findOneByNameAndUserId(name, userId);
    }

    @Override
    public void deleteFileByFileIdAndUserId(Integer fileId, Integer userId) {

        fileRepository.deleteFileByFileIdAndUserId(fileId, userId);
    }

    @Override
    public UserFile findUserFileByFileId(Integer fileId) throws FileNotFoundException {
        UserFile userFile = fileRepository.findOneByFileId(fileId);

            if(userFile == null)
                throw new FileNotFoundException();

        return userFile;
    }

}
