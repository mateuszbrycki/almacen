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
    public void updateFolderById(Integer folder_id, String folder_name) {
        this.folderRepository.updateFolderById(folder_id, folder_name);
    }

    @Override
    public void updateFolderPathById(String physical_path, Integer folderId) {
        this.folderRepository.updateFolderPathById(physical_path,folderId);
    }

    @Override
    public void deleteFolderById(Integer id) {
        this.folderRepository.delete(id);
    }

    @Override
    public Boolean checkIfParentIdExists(Integer userId, Integer folderId) {
        Folder folder = this.folderRepository.findOneByUserIdAndFolderId(userId, folderId);

        if (folder.getParent_folder_id() != 0) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean checkIfFolderWithNameExists(String physical_path, String folder_name) {
        Folder folder = this.folderRepository.findOneByFolderNameAndPhysicalPath(physical_path, folder_name);

        if (folder != null) {
            return true;
        }

        return false;
    }


    @Override
    public Integer getFolderIdByPhysicalPath(String physical_path) throws FolderNotFoundException {
        Folder folder = this.folderRepository.findOneByPhysicalPath(physical_path);

        if (folder == null) {
            throw new FolderNotFoundException();
        }

        return folder.getId();
    }

    @Override
    public String getPhysicalPathByFolderId(Integer folder_id) throws FolderNotFoundException {
        Folder folder = this.folderRepository.findOneByFolderId(folder_id);

        if (folder == null) {
            throw new FolderNotFoundException();
        }

        return folder.getPhysical_path();
    }

    @Override
    public String getFolderNameByFolderId(Integer folder_id) throws FolderNotFoundException {
        Folder folder = this.folderRepository.findOneByFolderId(folder_id);

        if (folder == null) {
            throw new FolderNotFoundException();
        }

        return folder.getFolder_name();
    }

    @Override
    public Folder findFolderById(Integer folder_id) throws FolderNotFoundException {
        Folder folder = this.folderRepository.findOne(folder_id);

        if (folder == null) {
            throw new FolderNotFoundException();
        }

        return folder;
    }

    @Override
    public Folder findFolderByPhysicalPath(String physical_path) throws FolderNotFoundException {
        Folder folder = this.folderRepository.findOneByPhysicalPath(physical_path);

        if (folder == null) {
            throw new FolderNotFoundException();
        }

        return folder;
    }

    @Override
    public List<Folder> findFoldersByPhysicalPath(String physical_path) throws FolderNotFoundException {

        List<Folder> folders = this.folderRepository.findAllByPhysicalPath(physical_path);

        if (folders == null) {
            throw new FolderNotFoundException();
        }

        return folders;
    }

    @Override
    public List<Folder> findFoldersByUserId(Integer userId) {
        List<Folder> folders = this.folderRepository.findByUserId(userId);

        return folders;
    }
}
