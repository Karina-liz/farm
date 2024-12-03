package com.app.farmacia.service.impl;

import com.app.farmacia.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class UploadServiceImpl implements UploadService {
    private static final String URL = "src/main/resources/static/upload/";

    @Override
    public String saveUpload(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            String encode = URLEncoder.encode(Objects.requireNonNull(file.getOriginalFilename()), StandardCharsets.UTF_8);
            Path path = Paths.get(URL + encode);
            Files.write(path, bytes);
            return encode;
        }
        return null;
    }

    @Override
    public boolean fileExists(String fileName) {
        File file = new File(URL + fileName);
        return file.exists();
    }

    @Override
    public void deleteUpload(String nombre) {
        File file = new File(URL + nombre);
        boolean delete = file.delete();
    }
}
