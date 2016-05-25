package com.almacen.module.folder;

import com.almacen.api.UrlSpace;


public class FolderUrls implements UrlSpace {

    public static final String FOLDER = "/folder";

    public static final String FOLDER_SHOW = "/folder_show";
    public static final String FOLDER_SHOW_FULL = FOLDER + FOLDER_SHOW;
    public static final String FOLDER_SHOW_FOLDER = FOLDER_SHOW + "/{folderId}";

    public static final String FOLDER_CREATE = "/folder_create";
    public static final String FOLDER_CREATE_FORM = FOLDER + FOLDER_CREATE;

    public static final String FOLDER_EDIT = "/folder_edit";
    public static final String FOLDER_EDIT_FORM = FOLDER + FOLDER_EDIT;

    public static final String FOLDER_SHARE = "/folder_share";
    public static final String FOLDER_SHARE_FORM = FOLDER + FOLDER_SHARE;

    public static final String FOLDER_DELETE = "/folder_delete";
    public static final String FOLDER_DELETE_FULL = FOLDER + FOLDER_DELETE;
}
