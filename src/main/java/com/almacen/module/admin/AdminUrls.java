package com.almacen.module.admin;

import com.almacen.api.UrlSpace;

public class AdminUrls implements UrlSpace {

    public static final String ADMIN = "/admin";
    public static final String ADMIN_LOGS = "/logs";

    public static final String ADMIN_LOGS_USER = ADMIN_LOGS + "/{userId}";
    public static final String ADMIN_LOGS_FULL = ADMIN + ADMIN_LOGS;
}
