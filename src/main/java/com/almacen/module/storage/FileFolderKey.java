package com.almacen.module.storage;

import com.almacen.module.file.UserFile;
import com.almacen.module.folder.Folder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class FileFolderKey implements Serializable {

    @Column(name = "fk_folder_id", nullable = false)
    private Integer folderId;

    @Column(name = "fk_file_id", nullable = false)
    private Integer userFileId;

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolder(Integer folderId) {
        this.folderId = folderId;
    }

    public Integer getUserFileId() {
        return userFileId;
    }

    public void setUserFile(Integer userFileId) {
        this.userFileId = userFileId;
    }
}
