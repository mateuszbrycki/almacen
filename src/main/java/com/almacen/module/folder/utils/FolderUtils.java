package com.almacen.module.folder.utils;


import java.io.File;

public class FolderUtils {

    public boolean folderDelete(File file) {
        if (!file.exists())
            return false;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                folderDelete(f);
            }
        }
        file.delete();
        return true;
    }
}
