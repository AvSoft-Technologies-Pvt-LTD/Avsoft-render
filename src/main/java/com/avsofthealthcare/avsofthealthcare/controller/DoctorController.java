package com.avsofthealthcare.avsofthealthcare.controller;

import com.avsofthealthcare.avsofthealthcare.dto.DoctorRegisterRequest;
import com.avsofthealthcare.avsofthealthcare.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/doctor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DoctorController {
    
    private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private DoctorService doctorService;

    // Simple test endpoint for file upload
    @PostMapping("/test-file")
    public ResponseEntity<?> testFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            logger.info("Test file upload endpoint called");
            logger.info("File name: {}", file.getOriginalFilename());
            logger.info("File size: {}", file.getSize());
            logger.info("Content type: {}", file.getContentType());
            
            Map<String, Object> response = new HashMap<>();
            response.put("fileName", file.getOriginalFilename());
            response.put("size", file.getSize());
            response.put("contentType", file.getContentType());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error in test file upload: ", e);
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerDoctor(
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("gender") String gender,
            @RequestParam("dob") String dob,
            @RequestParam("registrationNumber") String registrationNumber,
            @RequestParam("practiceType") String practiceType,
            @RequestParam("specialization") String specialization,
            @RequestParam("agreeDeclaration") boolean agreeDeclaration,
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "documents", required = false) MultipartFile[] documents) {
        
        try {
            logger.info("=== Starting Doctor Registration ===");
            logger.info("Basic Info - Email: {}, Name: {} {}", email, firstName, lastName);

            if (photo != null) {
                logger.info("Photo Details:");
                logger.info("- Name: {}", photo.getOriginalFilename());
                logger.info("- Size: {} bytes", photo.getSize());
                logger.info("- Content Type: {}", photo.getContentType());
                logger.info("- Is Empty: {}", photo.isEmpty());
            }

            if (documents != null) {
                logger.info("Documents Count: {}", documents.length);
                for (MultipartFile doc : documents) {
                    if (doc != null) {
                        logger.info("Document Details:");
                        logger.info("- Name: {}", doc.getOriginalFilename());
                        logger.info("- Size: {} bytes", doc.getSize());
                        logger.info("- Content Type: {}", doc.getContentType());
                        logger.info("- Is Empty: {}", doc.isEmpty());
                    }
                }
            }

            DoctorRegisterRequest request = new DoctorRegisterRequest();
            request.setEmail(email);
            request.setPhone(phone);
            request.setPassword(password);
            request.setConfirmPassword(confirmPassword);
            request.setFirstName(firstName);
            request.setLastName(lastName);
            request.setGender(gender);
            request.setDob(dob);
            request.setRegistrationNumber(registrationNumber);
            request.setPracticeType(practiceType);
            request.setSpecialization(specialization);
            request.setAgreeDeclaration(agreeDeclaration);

            if (photo != null && !photo.isEmpty()) {
                request.setPhoto(photo);
            }

            if (documents != null && documents.length > 0) {
                java.util.List<MultipartFile> docList = new java.util.ArrayList<>();
                for (MultipartFile doc : documents) {
                    if (doc != null && !doc.isEmpty()) {
                        docList.add(doc);
                    }
                }
                request.setDocuments(docList);
            }

            String response = doctorService.register(request);
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", response);
            return ResponseEntity.ok(successResponse);

        } catch (Exception e) {
            logger.error("Error in registration: ", e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Registration failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}

