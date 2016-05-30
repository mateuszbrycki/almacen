package com.almacen.module.storage;


import com.almacen.api.UrlSpace;

public class StorageUrls implements UrlSpace {
    public static final String STORAGE = "/storage";
    public static final String CONTENT = "/content";


    public class Api {
        public static final String FOLDER_CONTENT_FULL = StorageUrls.CONTENT + "/{folderId}";
        public static final String FOLDER_CONTENT = StorageUrls.STORAGE + StorageUrls.CONTENT;
    }
}
