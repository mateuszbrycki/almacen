package com.almacen.module.file.utils;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileSaver {

    public static void saveFile(MultipartFile file, File filePath) {
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
}