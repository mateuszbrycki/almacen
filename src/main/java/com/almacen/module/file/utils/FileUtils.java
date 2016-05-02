package com.almacen.module.file.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLConnection;

public class FileUtils {

    public static void saveFile(MultipartFile file, File filePath) {

        filePath.setWritable(true);

        if (!filePath.exists()) {
            filePath.mkdirs();
        }

            try {

                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new
                        FileOutputStream(filePath + "/" + file.getOriginalFilename()));
                FileCopyUtils.copy(file.getInputStream(), bufferedOutputStream);
                bufferedOutputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public static boolean deleteFile(File file) {

        if(file.exists()) {

            if(file.delete()) {
                return true;
            }
        }

        return false;
    }

    public static String getFileMimeType(String filename) {
        String mimeType = URLConnection.guessContentTypeFromName(filename);

        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        return mimeType;
    }
}
