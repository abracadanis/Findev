package com.example.demo.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private static final String path = "projectImages/";

    public String saveFileToFileSystem (MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "." + file.getContentType().split("/")[1];

        OutputStream stream = new FileOutputStream(path + fileName);
        file.getInputStream().transferTo(stream);

        return fileName;
    }

    public byte[] getFileAsByteArray (String fileName) throws IOException {
        byte[] ba;
        try (InputStream inputStream = new FileInputStream(path + fileName);){
            ba = inputStream.readAllBytes();
        }

        return ba;
    }

    public void deleteFileFromFileSystem (String fileName) throws IOException {
        if (!Files.deleteIfExists(Paths.get(path + fileName))) {
            throw new IOException("Could not delete file: " + fileName);
        }
    }
}
