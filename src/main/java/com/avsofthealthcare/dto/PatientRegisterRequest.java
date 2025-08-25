package com.avsofthealthcare.dto;


import jakarta.persistence.Column;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class PatientRegisterRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String confirmPassword;
    private String aadhaar;
    private Integer genderId;  // used for input
    private LocalDate dob;
    private String occupation;
    private String pinCode;
    private String city;
    private String district;
    private String state;
//    private String temporaryAddress;
//    private String permanentAddress;
//    private boolean sameAsPermanent;
    private boolean agreeDeclaration;


    private MultipartFile photo;
}
