package com.avsofthealthcare.avsofthealthcare.controller;

import com.avsofthealthcare.avsofthealthcare.dto.HospitalRegisterRequest;
import com.avsofthealthcare.avsofthealthcare.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> register(@ModelAttribute HospitalRegisterRequest request) {
        try {
            hospitalService.register(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("Hospital registered successfully");
    }
}