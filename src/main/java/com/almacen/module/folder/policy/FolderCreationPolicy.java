package com.almacen.module.folder.policy;


import org.springframework.stereotype.Component;

@Component("folderCreationPolicy")
public class FolderCreationPolicy {


    private static final String UPLOAD_PATH = "uploads";
    private static final String UPLOAD_PATH_TO_EDIT = "/";

    public String generateFolderPath(Integer userId) {
        return FolderCreationPolicy.UPLOAD_PATH + "/" + userId;
    }

    public String generateFolderEditablePath(String path, String folder_name) {
        return FolderCreationPolicy.UPLOAD_PATH_TO_EDIT + path + folder_name;
    }
}
