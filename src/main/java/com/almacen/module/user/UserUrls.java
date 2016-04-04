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

    public static final String USER_EDIT_MANAGEMENT = "/editmanagement";
    public static final String USER_EDIT_MANAGEMENT_FULL = USER + USER_EDIT_MANAGEMENT;

    public static final String USER_LIST = "/userlist";
    public static final String USER_LIST_FULL = USER + USER_LIST;

    public static final String USER_PASSWORD_CHANGE = USER_MANAGEMENT + "/password";
    public static final String USER_PASSWORD_CHANGE_FORM = USER + USER_PASSWORD_CHANGE;

    public static final String USER_USERNAME_CHANGE = USER_MANAGEMENT + "/username";
    public static final String USER_USERNAME_CHANGE_FORM = USER + USER_USERNAME_CHANGE;

    public static final String USER_DELETE = USER_MANAGEMENT + "/delete";
    public static final String USER_DELETE_FORM = USER + USER_DELETE;

    public static final String USER_CHANGE_ROLE = USER_MANAGEMENT + "/changerole";
    public static final String USER_CHANGE_ROLE_FORM = USER + USER_CHANGE_ROLE;

    public static final String ADMIN_PASSWORD_CHANGE = USER_MANAGEMENT + "/adminpassword";
    public static final String ADMIN_PASSWORD_CHANGE_FORM = USER + ADMIN_PASSWORD_CHANGE;

    public static final String ADMIN_USERNAME_CHANGE = "/adminusername";
    public static final String ADMIN_USERNAME_CHANGE_FORM = USER + ADMIN_USERNAME_CHANGE;

    public static final String ADMIN_DELETE = USER_MANAGEMENT + "/admindelete";
    public static final String ADMIN_DELETE_FORM = USER + ADMIN_DELETE;



    public class Api {
        public static final String USER = "/api"  + UserUrls.USER;

        public static final String USER_USERNAME = UserUrls.Api.USER + "/{username}";
        public static final String USERNAME = "/{username}";

    }
}
