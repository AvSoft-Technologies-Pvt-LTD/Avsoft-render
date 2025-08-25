package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.LabRegisterRequest;
import com.avsofthealthcare.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth/lab")
public class LabController {

    @Autowired
    private LabService labService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> register(@ModelAttribute LabRegisterRequest request) {
        try {
            labService.register(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Lab registered successfully");
    }
}