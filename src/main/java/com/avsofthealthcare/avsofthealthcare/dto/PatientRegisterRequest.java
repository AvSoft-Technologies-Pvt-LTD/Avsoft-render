package com.avsofthealthcare.avsofthealthcare.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientRegisterRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String confirmPassword;
    private String aadhaar;
    private String gender;
    private LocalDate dob;
    private String occupation;
    private String temporaryAddress;
    private String permanentAddress;
    private boolean isSameAsPermanent;
    private boolean agreDeclaration;

    private MultipartFile photo;
}
