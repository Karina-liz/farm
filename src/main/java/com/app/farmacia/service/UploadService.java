package com.app.farmacia.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    String saveUpload(MultipartFile file) throws IOException;

    boolean fileExists(String fileName);

    void deleteUpload(String nombre);
}
