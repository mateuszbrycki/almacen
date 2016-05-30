package com.almacen.module.storage;

import javax.persistence.*;

@Entity
@Table(name = "file_folder")
public class FileFolder {

    @EmbeddedId
    private FileFolderKey fileFolderKey;

    public FileFolderKey getFileFolderKey() {
        return fileFolderKey;
    }

    public void setFileFolderKey(FileFolderKey fileFolderKey) {
        this.fileFolderKey = fileFolderKey;
    }
}
