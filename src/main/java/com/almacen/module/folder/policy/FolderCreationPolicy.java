package com.almacen.module.folder.policy;


import org.springframework.stereotype.Component;

@Component("folderCreationPolicy")
public class FolderCreationPolicy {


    private static final String UPLOAD_PATH = "storage";
    private static final String UPLOAD_PATH_TO_EDIT = "/";

    public String generateFolderPath(Integer userId) {
        return FolderCreationPolicy.UPLOAD_PATH + "/" + userId;
    }

    public String generateFolderEditablePath(String path) {
        return FolderCreationPolicy.UPLOAD_PATH_TO_EDIT + path;
    }
}
