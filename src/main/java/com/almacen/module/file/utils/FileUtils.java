package com.almacen.module.file.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtils {

    public static void saveFile(MultipartFile file, File filePath) {

        filePath.setWritable(true);

        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        FileSaver.saveFile(file, filePath);

    }

    public static boolean deleteFile(File file) {

        if (file.exists()) {

            if (file.delete()) {
                return true;
            }
        }

        return false;
    }
}
