package com.example.minio.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageService {
    String uploadImage(MultipartFile imagePath);

    String getImage(MultipartFile imageName);

}
