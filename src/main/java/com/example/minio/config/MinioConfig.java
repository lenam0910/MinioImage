package com.example.minio.config;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.Data;

@Data
@Configuration
public class MinioConfig {

    @Value("${minio.url}")
    private String url;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Bean
    MinioClient minioClient() {
        try {
            MinioClient client = MinioClient.builder()
                    .endpoint(url)
                    .credentials(accessKey, secretKey)
                    .build();
            boolean bucketExists = client.bucketExists(BucketExistsArgs
                    .builder()
                    .bucket(bucketName)
                    .build());
            if (!bucketExists) {
                client.makeBucket(MakeBucketArgs
                        .builder()
                        .bucket(bucketName)
                        .build());
            }
            return client;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create MinIO client or bucket", e);
        }

    }
}
