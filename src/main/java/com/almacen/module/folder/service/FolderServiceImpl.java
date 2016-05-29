package com.almacen.module.folder.service;

import com.almacen.module.folder.Folder;
import com.almacen.module.folder.exception.FolderNotFoundException;
import com.almacen.module.folder.repository.FolderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("folderService")
@Transactional
public class FolderServiceImpl implements FolderService {

    @Resource
    private FolderRepository folderRepository;

    @Override
    public void saveFolder(Folder folder) {
        this.folderRepository.save(folder);
    }

    @Override
    public void updateFolder(Folder folder) {
    }

    @Override
    public void updateFolderById(Integer folderId, String folderName) {
        this.folderRepository.updateFolderById(folderId, folderName);
    }

    @Override
    public void updateFolderPathById(String physicalPath, Integer folderId) {
        this.folderRepository.updateFolderPathById(physicalPath, folderId);
    }

    @Override
    public void deleteFolderById(Integer id) {
        this.folderRepository.delete(id);
    }

    @Override
    public Boolean checkIfParentIdExists(Integer userId, Integer folderId) {
        Folder folder = this.folderRepository.findOneByUserIdAndFolderId(userId, folderId);

        if (folder.getParentFolderId() != 0) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean checkIfFolderWithNameExists(String physicalPath, String folderName) {
        Folder folder = this.folderRepository.findOneByFolderNameAndPhysicalPath(physicalPath, folderName);

        if (folder != null) {
            return true;
        }

        return false;
    }


    @Override
    public Integer getFolderIdByPhysicalPath(String physicalPath) throws FolderNotFoundException {

        Folder folder = this.folderRepository.findOneByPhysicalPath(physicalPath);

        if (folder == null) {
            throw new FolderNotFoundException();
        }

        return folder.getId();
    }

    @Override
    public String getPhysicalPathByFolderId(Integer folderId) throws FolderNotFoundException {
        Folder folder = this.folderRepository.findOneByFolderId(folderId);

        if (folder == null) {
            throw new FolderNotFoundException();
        }

        return folder.getPhysicalPath();
    }

    @Override
    public String getFolderNameByFolderId(Integer folderId) throws FolderNotFoundException {
        Folder folder = this.folderRepository.findOneByFolderId(folderId);

        if (folder == null) {
            throw new FolderNotFoundException();
        }

        return folder.getFolderName();
    }

    @Override
    public Folder findFolderById(Integer folderId) throws FolderNotFoundException {
        Folder folder = this.folderRepository.findOne(folderId);

        if (folder == null) {
            throw new FolderNotFoundException();
        }

        return folder;
    }

    @Override
    public Folder findFolderByPhysicalPath(String physicalPath) throws FolderNotFoundException {
        Folder folder = this.folderRepository.findOneByPhysicalPath(physicalPath);

        if (folder == null) {
            throw new FolderNotFoundException();
        }

        return folder;
    }

    @Override
    public List<Folder> findFoldersByPhysicalPath(String physicalPath) throws FolderNotFoundException {

        List<Folder> folders = this.folderRepository.findAllByPhysicalPath(physicalPath);

        if (folders == null) {
            throw new FolderNotFoundException();
        }

        return folders;
    }

    @Override
    public Folder findUserDefaultFolder(Integer userId) {

        return folderRepository.findUserDefaultFolder(userId, "" + userId);
    }

    @Override
    public List<Folder> findFoldersFromUserDefaultFolder(Integer folderId) {
        return folderRepository.findFoldersFromDefaultUserFolder(folderId);
    }

    @Override
    public List<Folder> findFoldersByUserId(Integer userId) {
        List<Folder> folders = this.folderRepository.findByUserId(userId);

        return folders;
    }
}
