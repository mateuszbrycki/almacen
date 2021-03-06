package com.almacen.module.folder;

import com.almacen.module.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "folder")
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private Integer id;

    @NotNull
    @Column(name = "folder_name")
    private String folderName;

    @Column(name = "parent_folder_id")
    @JsonIgnore
    private Integer parentFolderId;

    @NotNull
    @Column(name = "physical_path")
    @JsonIgnore
    private String physicalPath;

    @OneToOne
    @JoinColumn(name = "fk_owner_id")
    @JsonIgnore
    private User user;

    @Column(name = "is_default_folder")
    private Boolean isDefaultFolder;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getParentFolderId() {
        if(parentFolderId == null)
            return 999999999;
        else
            return parentFolderId; }

    public void setParentFolderId(int parentFolderId) {
        this.parentFolderId = parentFolderId;
    }

    public String getPhysicalPath() {
        return physicalPath;
    }

    public void setPhysicalPath(String physicalPath) {
        this.physicalPath = physicalPath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean isDefaultFolder() { return this.isDefaultFolder; }

    public void setIsDefaultFolder(Boolean isDefaultFolder) {
        this.isDefaultFolder = isDefaultFolder;
    }
}
