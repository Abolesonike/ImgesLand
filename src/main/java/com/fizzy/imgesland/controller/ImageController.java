package com.fizzy.imgesland.controller;

import com.fizzy.imgesland.entity.ResponseData;
import com.fizzy.imgesland.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseData uploadImage(MultipartFile image) {
        return imageService.upload(image);
    }

    @GetMapping("/get")
    public ResponseData getImage() {
        return imageService.get();
    }
}
