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

    public static final String ADMIN_STATISTICS = ADMIN + "/statistics" ;
    public static final String ADMIN_STATISTICS_FULL = ADMIN_STATISTICS + "/";

    public static final String ADMIN_SPECIFIC_STATISTIC = "/statistic";
    public static final String ADMIN_SPECIFIC_STATISTIC_ID = ADMIN + ADMIN_SPECIFIC_STATISTIC + "/{userId}";
    public static final String ADMIN_SPECIFIC_STATISTIC_FULL = ADMIN + ADMIN_SPECIFIC_STATISTIC;


    public class Api {
        public static final String ADMIN = "/api"  + AdminUrls.ADMIN;
        public static final String ADMIN_FILE = ADMIN + "/file";


        public static final String ADMIN_FILE_EXTENSION =  "/extensions";
        public static final String ADMIN_FILE_EXTENSION_FULL =  ADMIN_FILE + "/extensions";

        public static final String ADMIN_MAXIMUM_SIZE_FILE = "/maximum_size";
        public static final String ADMIN_MAXIMUM_SIZE_FILE_FULL = ADMIN_FILE + "/maximum_size";

    }

    public static final String ADMIN_EDIT_MANAGEMENT = "/editmanagement";
    public static final String ADMIN_EDIT_MANAGEMENT_ID = ADMIN_EDIT_MANAGEMENT + "/{userId}";
    public static final String ADMIN_EDIT_MANAGEMENT_FULL = ADMIN + ADMIN_EDIT_MANAGEMENT;

    public static final String USER_LIST = "/userList";
    public static final String USER_LIST_FULL = ADMIN + USER_LIST;

    public static final String ADMIN_CHANGE_ROLE = ADMIN_EDIT_MANAGEMENT + "/changerole";
    public static final String ADMIN_CHANGE_ROLE_FORM = ADMIN + ADMIN_CHANGE_ROLE;

    public static final String ADMIN_PASSWORD_CHANGE = ADMIN_EDIT_MANAGEMENT + "/password";
    public static final String ADMIN_PASSWORD_CHANGE_FORM = ADMIN + ADMIN_PASSWORD_CHANGE;

    public static final String ADMIN_USERNAME_CHANGE = ADMIN_EDIT_MANAGEMENT + "/username";
    public static final String ADMIN_USERNAME_CHANGE_FORM = ADMIN + ADMIN_USERNAME_CHANGE;

    public static final String ADMIN_DELETE = ADMIN_EDIT_MANAGEMENT + "/delete";
    public static final String ADMIN_DELETE_FORM = ADMIN + ADMIN_DELETE;
}
