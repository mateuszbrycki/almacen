//package com.almacen.module.storage;
//
//import com.almacen.module.file.UserFile;
//import com.almacen.module.folder.Folder;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Table(name = "file_folder")
//public class FileFolder {
//
//    @NotNull
//    @JsonIgnore
//    @JoinColumn(name = "fk_folder_id")
//    private Folder folder;
//
//    @NotNull
//    @JsonIgnore
//    @JoinColumn(name = "fk_file_id")
//    private UserFile userFile;
//
//    public Folder getFolder() {
//        return folder;
//    }
//
//    public void setFolder(Folder folder) {
//        this.folder = folder;
//    }
//
//    public UserFile getUserFile() {
//        return userFile;
//    }
//
//    public void setUserFile(UserFile userFile) {
//        this.userFile = userFile;
//    }
//}
