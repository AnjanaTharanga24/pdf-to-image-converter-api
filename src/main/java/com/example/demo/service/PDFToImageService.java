package com.example.demo.service;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public interface PDFToImageService {
    byte[] convertPDFToImage(File pdfFile);
    byte[] convertMultipartFileToImage(MultipartFile file) throws Exception;
}