package com.avsofthealthcare.avsofthealthcare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/test")
public class TestController {
    
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("Test upload endpoint called");
        try {
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
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/ping")
    @ResponseBody
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Test controller is working!");
    }
} 