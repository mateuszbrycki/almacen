package com.almacen.module.admin;

import com.almacen.api.UrlSpace;

public class AdminUrls implements UrlSpace {

    public static final String ADMIN = "/admin";
    public static final String ADMIN_LOGS = ADMIN + "/logs";
    public static final String ADMIN_FILE = ADMIN + "/file";

    public static final String ADMIN_LOGS_USER = "/{userId}";
    public static final String ADMIN_LOGS_USER_FULL = ADMIN_LOGS + ADMIN_LOGS_USER;
    public static final String ADMIN_LOGS_FULL = ADMIN_LOGS + "/";

    public static final String ADMIN_FILE_FULL = ADMIN_FILE + "/";

    public class Api {
        public static final String ADMIN = "/api"  + AdminUrls.ADMIN;
        public static final String ADMIN_FILE = ADMIN + "/file";


        public static final String ADMIN_FILE_EXTENSION =  "/extensions";
        public static final String ADMIN_FILE_EXTENSION_FULL =  ADMIN_FILE + "/extensions";

    }

}
