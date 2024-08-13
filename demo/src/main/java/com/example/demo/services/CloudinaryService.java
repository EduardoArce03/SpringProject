package com.example.demo.services;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


public interface CloudinaryService  {
    //Subir imagen
    Map upload(MultipartFile file) throws IOException;
    //Borrar imagen
    Map delete(String id) throws IOException;
}
