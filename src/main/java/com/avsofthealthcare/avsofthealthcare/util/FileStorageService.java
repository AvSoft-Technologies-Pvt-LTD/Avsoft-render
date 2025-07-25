package com.avsofthealthcare.avsofthealthcare.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService(@Value("${app.file.upload-dir}") String uploadDir) {
        try {
            this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(this.fileStorageLocation);
            System.out.println("Storage location initialized: " + this.fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create upload directory: " + uploadDir, ex);
        }
    }

    public String storeFile(MultipartFile file, String subDir) throws IOException {
        try {
            // Validate file
            if (file == null) {
                throw new IllegalArgumentException("File is null");
            }
            if (file.isEmpty()) {
                throw new IllegalArgumentException("File is empty");
            }

            // Get and clean filename
            String originalFilename = StringUtils.cleanPath(
                file.getOriginalFilename() != null ? file.getOriginalFilename() : "file"
            );
            
            // Create unique filename
            String filename = System.currentTimeMillis() + "_" + originalFilename;

            // Create and validate target directory
            Path targetLocation = this.fileStorageLocation.resolve(subDir).normalize();
            if (!targetLocation.toFile().exists()) {
                Files.createDirectories(targetLocation);
            }

            // Validate the file path
            Path filePath = targetLocation.resolve(filename);
            if (!filePath.getParent().equals(targetLocation)) {
                throw new IllegalArgumentException("Cannot store file outside current directory");
            }

            // Copy the file
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File stored successfully at: " + filePath);
                return Paths.get(subDir, filename).toString().replace("\\", "/");
            }

        } catch (IOException ex) {
            throw new IOException("Could not store file. Error: " + ex.getMessage(), ex);
        }
    }
}
