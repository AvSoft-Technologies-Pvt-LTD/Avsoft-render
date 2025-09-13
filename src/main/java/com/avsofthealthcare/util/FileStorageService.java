package com.avsofthealthcare.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorageService {

    @Value("${app.file.upload-dir}")
    private final Path fileStorageLocation;

	private static final List<String> ALLOWED_FILE_EXTENSIONS = List.of(
			"pdf", "doc", "docx", "xls", "xlsx", "jpg", "jpeg", "png", "gif", "txt","avif"
	);

	private static final List<String> ALLOWED_MIME_TYPES = List.of(
			"application/pdf",
			"application/msword",
			"application/vnd.openxmlformats-officedocument.wordprocessingml.document",
			"application/vnd.ms-excel",
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
			"image/jpeg",
			"image/png",
			"image/avif",
			"image/gif",
			"text/plain"
	);


	public FileStorageService(@Value("${app.file.upload-dir}") String uploadDir) {
        try {
            this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(this.fileStorageLocation);
            System.out.println("Storage location initialized: " + this.fileStorageLocation);
        } catch (IOException ex) {
            throw new RuntimeException("Could not create upload directory: " + uploadDir, ex);
        }
    }


	private void validateSafeFileType(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		String contentType = file.getContentType();

		String extension = StringUtils.getFilenameExtension(originalFilename);
		if (extension == null || !ALLOWED_FILE_EXTENSIONS.contains(extension.toLowerCase())) {
			throw new IllegalArgumentException("File extension not allowed: ." + extension);
		}

		if (contentType == null || !ALLOWED_MIME_TYPES.contains(contentType)) {
			throw new IllegalArgumentException("MIME type not allowed: " + contentType);
		}
	}


	// ===== Single File Upload =====
	public String uploadSingleFile(MultipartFile file, String subDir) throws IOException {
		validateSafeFileType(file); // ✅ Added
		return storeFile(file, subDir);
	}


	public String uploadSingleFileWithName(MultipartFile file, String subDir, String customFileName) throws IOException {
		if (file == null) {
			throw new IllegalArgumentException("File is null");
		}
		if (file.isEmpty()) {
			throw new IllegalArgumentException("File is empty");
		}

		validateSafeFileType(file); // ✅ Added

		// Ensure directory exists
		Path targetLocation = this.fileStorageLocation.resolve(subDir).normalize();
		Files.createDirectories(targetLocation);

		// Ensure no directory traversal
		Path filePath = targetLocation.resolve(customFileName);
		if (!filePath.getParent().equals(targetLocation)) {
			throw new IllegalArgumentException("Cannot store file outside current directory");
		}

		// Store file
		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File stored at: " + filePath);
			return Paths.get(subDir, customFileName).toString().replace("\\", "/");
		}
	}


	// ===== Multiple Files Upload =====
	public List<String> uploadMultipleFiles(List<MultipartFile> files, String subDir) throws IOException {
		List<String> storedPaths = new ArrayList<>();
		for (MultipartFile file : files) {
			validateSafeFileType(file); // ✅ Added
			storedPaths.add(storeFile(file, subDir));
		}
		return storedPaths;
	}


	// ===== Download File =====
    public Path downloadFile(String relativePath) {
	    Path filePath = this.fileStorageLocation.resolve(relativePath).normalize();
	   // System.out.println("Trying to download from path: " + filePath);
	    if (!Files.exists(filePath)) {
		    throw new RuntimeException("File not found: " + filePath);
	    }
	    return filePath;
    }


	// ===== Delete File =====

    public void deleteFile(String subDir, String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(Paths.get(subDir, fileName)).normalize();
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                System.out.println("Deleted file: " + filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not delete file: " + fileName, e);
        }
    }



    // ===== Internal Utility =====
    private String storeFile(MultipartFile file, String subDir) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File is null");
        }
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

	    validateSafeFileType(file);

        // Clean filename
        String originalFilename = StringUtils.cleanPath(
                file.getOriginalFilename() != null ? file.getOriginalFilename() : "file"
        );

        // Create unique filename
        String filename = System.currentTimeMillis() + "_" + originalFilename;

        // Create subdirectory if not exists
        Path targetLocation = this.fileStorageLocation.resolve(subDir).normalize();
        Files.createDirectories(targetLocation);

        // Validate storage path
        Path filePath = targetLocation.resolve(filename);
        if (!filePath.getParent().equals(targetLocation)) {
            throw new IllegalArgumentException("Cannot store file outside current directory");
        }

        // Store file
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File stored at: " + filePath);
            return Paths.get(subDir, filename).toString().replace("\\", "/");
        }
    }
}

