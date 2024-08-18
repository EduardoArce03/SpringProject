package com.example.demo.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl() {
        Map<String, String> valuesMap = new HashMap<>();
            valuesMap.put("cloud_name", "dizypcgso");
            valuesMap.put("api_key", "595367341334863");
            valuesMap.put("api_secret", "3XPaLofycygbnjKb7FdtRMDRT1s");
            cloudinary = new Cloudinary(valuesMap);
    }

    private File convert(MultipartFile multiPartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multiPartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multiPartFile.getBytes());
        fos.close();
        return convFile;
    }

    @Override
    public Map upload(MultipartFile multipartFile) throws IOException {
        // TODO Auto-generated method stub
        File convFile = convert(multipartFile);
        Map result = cloudinary.uploader().upload(convFile, ObjectUtils.emptyMap());
        if (!Files.deleteIfExists(convFile.toPath())){
            throw new IOException("No se pudo eliminar el archivo temporal" + convFile.getAbsolutePath());
        }
        return result;
    }
    
    @Override
    public Map delete(String id) throws IOException {
        // TODO Auto-generated method stub
        return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }
}