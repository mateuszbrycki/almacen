package com.almacen.module.file;

import com.almacen.api.UrlSpace;

public class FileUrls implements UrlSpace {

    public static final String FILE = "/file";
    public static final String FILE_ID = "/{fileId}";
    public static final String FILE_DELETE = "/delete";

    public static final String FILE_UPLOAD = "/upload";
    public static final String FILE_DOWNLOAD = "/download";

    public static final String FILE_UPLOAD_FULL = FileUrls.FILE + FileUrls.FILE_UPLOAD;

    public class Api {

        public static final String FILE_DELETE_ID = FileUrls.FILE_DELETE + FileUrls.FILE_ID;
        public static final String FILE_DELETE_FULL = FileUrls.FILE + FileUrls.FILE_DELETE;
    }
}
