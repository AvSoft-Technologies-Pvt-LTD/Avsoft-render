package com.avsofthealthcare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {
    
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    //Requires JWT
    @PreAuthorize("isAuthenticated()")
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file) {
        logger.info("Test upload endpoint called");
        try {
            if (file == null || file.isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "No file uploaded or file is empty");
                return ResponseEntity.badRequest().body(error);
            }


            logger.info("Received file: {}", file.getOriginalFilename());
            logger.info("File size: {}", file.getSize());
            logger.info("Content type: {}", file.getContentType());

            Map<String, Object> response = new HashMap<>();
            response.put("fileName", file.getOriginalFilename());
            response.put("size", file.getSize());
            response.put("contentType", file.getContentType());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error in test upload: ", e);
            Map<String, String> error = new HashMap<>();
            error.put("error", "Upload failed: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    //Public/healthcheck
    @PreAuthorize("permitAll()")
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Test controller is working!");
    }
} 