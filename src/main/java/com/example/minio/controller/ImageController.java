package com.example.minio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.minio.service.ApiResponse;
import com.example.minio.service.ImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("img")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ApiResponse<Object> postMethodName(@RequestParam("image") MultipartFile image) {

        return ApiResponse.builder()
                .message("Image uploaded successfully")
                .data(imageService.uploadImage(image))
                .success(true)
                .build();
    }

}