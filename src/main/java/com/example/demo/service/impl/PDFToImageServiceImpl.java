package com.example.demo.service.impl;

import com.example.demo.service.PDFToImageService;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

@Service
public class PDFToImageServiceImpl implements PDFToImageService {

    @Override
    public byte[] convertMultipartFileToImage(MultipartFile file) throws Exception {
        File tempFile = File.createTempFile("upload-", ".pdf");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }

        try {
            return convertPDFToImage(tempFile);
        } finally {
            tempFile.delete();
        }
    }

    @Override
    public byte[] convertPDFToImage(File pdfFile) {
        try {
            File outputFile = File.createTempFile("output-", ".png");

            ConvertCmd cmd = new ConvertCmd(true);
            IMOperation op = new IMOperation();
            op.density(300);
            op.addImage(pdfFile.getAbsolutePath() + "[0]");
            op.resize(1000);
            op.quality(90.0);
            op.addImage(outputFile.getAbsolutePath());

            cmd.run(op);

            byte[] imageBytes = Files.readAllBytes(outputFile.toPath());
            outputFile.delete();

            return imageBytes;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert PDF to image", e);
        }
    }
}