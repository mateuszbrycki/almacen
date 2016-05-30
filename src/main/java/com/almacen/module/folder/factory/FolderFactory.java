package com.almacen.module.folder.factory;


import com.almacen.module.folder.Folder;
import com.almacen.module.folder.FolderAbstractFactory;
import com.almacen.module.folder.dto.FolderDTO;

public class FolderFactory implements FolderAbstractFactory {

    @Override
    public Folder createFromDTO(FolderDTO folderDTO) {

        Folder folder = new Folder();
        folder.setFolderName(folderDTO.getFolder_name());
        folder.setPhysicalPath(folderDTO.getPhysical_path());

        return folder;
    }
}
