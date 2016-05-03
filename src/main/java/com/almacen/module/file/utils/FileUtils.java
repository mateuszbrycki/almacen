package com.almacen.module.file.utils;

import com.almacen.module.file.UserFile;
import com.almacen.module.user.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLConnection;

public class FileUtils {

    public void saveFile(MultipartFile file, File filePath) {

        filePath.setWritable(true);

        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        FileSaver.saveFile(file, filePath);

    }

    public boolean deleteFile(File file) {

        if (file.exists()) {

            if (file.delete()) {
                return true;
            }
        }

        return false;
    }

    public String getFileMimeType(String filename) {
        String mimeType = URLConnection.guessContentTypeFromName(filename);

        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        return mimeType;
    }

    public UserFile mapUserFileObject(MultipartFile file, User user) {
        UserFile userFile = new UserFile();

        userFile.setName(file.getOriginalFilename());
        userFile.setExtension(this.getFileExtension(file.getOriginalFilename()));
        userFile.setSize(file.getSize());
        userFile.setUser(user);

        return userFile;
    }

    public String getFileExtension(String filename) {
        String[] fileArray = filename.split("\\.");

        return fileArray[fileArray.length - 1];
    }
}
