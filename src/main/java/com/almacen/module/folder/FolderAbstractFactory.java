package com.almacen.module.folder;

import com.almacen.AbstractFactory;
import com.almacen.module.folder.dto.FolderDTO;

public interface FolderAbstractFactory extends AbstractFactory {

    Folder createFromDTO(FolderDTO folderDTO);
}
