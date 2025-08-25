package com.avsofthealthcare.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class LabRegisterRequest {
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;

    private String centerType;
    private String centerName;
    private String ownerFullName;
    private String availableTest;
    private String labregistrationNumber;
    private String scanServices;
    private String specialServices;
    private String licenseNumber;
    private String lablocation;
    private String labgstNumber;
    private String certificateTypes;

    private boolean agreeDeclaration;

    private MultipartFile certificates;
}
