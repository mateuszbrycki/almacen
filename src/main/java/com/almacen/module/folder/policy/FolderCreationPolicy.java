package com.almacen.module.folder.policy;


import org.springframework.stereotype.Component;

@Component("folderCreationPolicy")
public class FolderCreationPolicy {


    private static final String UPLOAD_PATH = "uploads";

    public String generateFolderPath(Integer userId) {
        return FolderCreationPolicy.UPLOAD_PATH + "/" + userId;
    }
}
