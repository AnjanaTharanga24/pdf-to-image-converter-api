package com.example.demo.controller;

import com.example.demo.service.PDFToImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class PDFController {

    private final PDFToImageService pdfToImageService;

    @PostMapping(value = "/convert", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] convertPdfToImage(@RequestParam("file") MultipartFile file) throws Exception {
        return pdfToImageService.convertMultipartFileToImage(file);
    }
}