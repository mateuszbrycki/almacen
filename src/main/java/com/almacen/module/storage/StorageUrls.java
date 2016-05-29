package com.almacen.module.storage;


import com.almacen.api.UrlSpace;

public class StorageUrls implements UrlSpace {
    public static final String STORAGE = "storage/";


    public class Api {
        public static final String FOLDER_CONTENT = "folder_content/{folderId}";
        public static final String FOLDER_CONTENT_FULL = StorageUrls.STORAGE + Api.FOLDER_CONTENT;
    }
}
