package com.example.demo.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Image;

public interface ImageService {
    Image uploadImage(MultipartFile file) throws IOException;

    void deleteImage(Image image) throws IOException;
}
