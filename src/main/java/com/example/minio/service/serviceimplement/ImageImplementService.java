package com.example.minio.service.serviceimplement;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.minio.config.MinioConfig;
import com.example.minio.service.ImageService;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageImplementService implements ImageService {

    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Override
    public String uploadImage(MultipartFile imagePath) {
        String fileName = UUID.randomUUID().toString() + "_" + imagePath.getOriginalFilename();
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(imagePath.getInputStream(), imagePath.getSize(), -1)
                            .contentType(imagePath.getContentType())
                            .build());
            return "Image: " + fileName;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload image to MinIO", e);
        }
    }

    @Override
    public String getImage(MultipartFile imageName) {
        // Implementation for retrieving an image from MinIO
        return "Image URL or path";
    }

}
