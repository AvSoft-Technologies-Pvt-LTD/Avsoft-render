package com.avsofthealthcare.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PatientResponseDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String aadhaar;
    private String gender;
	private int gender_id;
    private LocalDate dob;
    private String email;
    private String occupation;
    private String pinCode;
    private String city;
    private String district;
    private String state;

//    private String temporaryAddress;
//    private String permanentAddress;
//    private boolean sameAsPermanent;
    private boolean agreeDeclaration;
    private String photo;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
