package com.almacen.module.user;


import com.almacen.api.UrlSpace;

public class UserUrls implements UrlSpace {
    public static final String USER = "/user";

    public static final String USER_LOGOUT = "/logout";
    public static final String USER_LOGOUT_FULL = USER + USER_LOGOUT;

    public static final String USER_REGISTER = "/register";
    public static final String USER_REGISTER_FORM = USER + USER_REGISTER;

    public static final String USER_MANAGEMENT = "/management";
    public static final String USER_MANAGEMENT_FULL = USER + USER_MANAGEMENT;

    public static final String USER_PASSWORD_CHANGE = USER_MANAGEMENT + "/password";
    public static final String USER_PASSWORD_CHANGE_FORM = USER + USER_PASSWORD_CHANGE;

    public static final String USER_USERNAME_CHANGE = USER_MANAGEMENT + "/username";
    public static final String USER_USERNAME_CHANGE_FORM = USER + USER_USERNAME_CHANGE;

    public static final String USER_DELETE = USER_MANAGEMENT + "/delete";
    public static final String USER_DELETE_FORM = USER + USER_DELETE;

    public class Api {
        public static final String USER = "/api" + UserUrls.USER;

        public static final String USER_USERNAME = UserUrls.Api.USER + "/{username}";
        public static final String USERNAME = "/{username}";

    }
}
