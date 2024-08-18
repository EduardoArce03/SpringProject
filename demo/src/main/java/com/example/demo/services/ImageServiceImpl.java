package com.example.demo.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Image;
import com.example.demo.repositories.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {
    
    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;

    public ImageServiceImpl(CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        // TODO Auto-generated method stub
        Map result = cloudinaryService.upload(file);
        String imageUrl = (String) result.get("url");
        String imageId = (String) result.get("public_id");
        Image image = new Image(file.getOriginalFilename(), imageUrl, imageId);
        return imageRepository.save(image);
    }
    @Override
    public void deleteImage(Image image) throws IOException {
        // TODO Auto-generated method stub
        cloudinaryService.delete(image.getImageId());
        imageRepository.deleteById(image.getId());
    }
}
