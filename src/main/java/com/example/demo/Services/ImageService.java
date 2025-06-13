package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {

    private static final String path = "classpath:projectImages";

    public String saveFileToFileSystem (MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        String filePath = path + File.separator + fileName;

        File f = new File(path);

        if(!f.exists()) {
            f.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

//    public InputStream getResourceFile (String fileName) {
//        String filePath = path + File.separator + fileName;
//
//        Files.
//    }
}
