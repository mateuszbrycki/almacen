package com.almacen.module.file.service;


import com.almacen.module.file.UserFile;
import com.almacen.module.file.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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
    public List<UserFile> findUserFilesByFolderId(Integer folderId) {
        return this.fileRepository.findByFolderId(folderId);
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

        if (userFile == null)
            throw new FileNotFoundException();

        return userFile;
    }

    @Override
    public Long getWholeSizeUserFiles(Integer userId) {
        List<UserFile> userFiles = fileRepository.findByUserId(userId);
        Long wholeSize = 0l;
        for (UserFile userFile : userFiles)
            wholeSize += userFile.getSize();
        return wholeSize;
    }

    @Override
    public List<String> getUserFilesAllExtension(Integer userId) {
        List<UserFile> userFiles = fileRepository.findByUserId(userId);
        ArrayList<String> extensionList = new ArrayList<>();
        for (UserFile userFile : userFiles)
            extensionList.add(userFile.getExtension());
        return extensionList;

    }

    @Override
    public HashMap<String, Integer> getUserFilesAllQuantity(Integer userId) {
        List<String> extensionList = getUserFilesAllExtension(userId);
        HashMap<String, Integer> quantity = new HashMap<String, Integer>();

        for (int i = 0; i < extensionList.size(); ++i) {
            String extension = extensionList.get(i);
            if (quantity.containsKey(extension))
                quantity.put(extension, quantity.get(extension) + 1);
            else
                quantity.put(extension, 1);
        }
        return quantity;
    }

    @Override
    public void deleteFilesInFolder(Integer folderId) {
        fileRepository.deleteFilesInFolder(folderId);
    }

    @Override
    public UserFile findFileInFolder(String filename, Integer folderId) {
        return fileRepository.findUserFileInFolder(filename, folderId);
    }
}
