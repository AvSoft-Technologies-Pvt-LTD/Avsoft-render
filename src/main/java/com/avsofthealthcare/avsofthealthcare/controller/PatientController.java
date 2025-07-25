package com.avsofthealthcare.avsofthealthcare.controller;

import com.avsofthealthcare.avsofthealthcare.dto.PatientRegisterRequest;
import com.avsofthealthcare.avsofthealthcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.time.LocalDate;


@RestController
@RequestMapping("/api/auth/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String text) {
                        setValue(LocalDate.parse(text));
                    }
                });
    }


    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> register(@ModelAttribute PatientRegisterRequest request) {

        try {
            patientService.register(request);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to process file upload");
        }

        // patientService.register(request);
        return ResponseEntity.ok("Patient registered successfully");
    }
}
