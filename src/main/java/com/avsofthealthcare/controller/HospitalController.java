package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.HospitalRegisterRequest;
import com.avsofthealthcare.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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