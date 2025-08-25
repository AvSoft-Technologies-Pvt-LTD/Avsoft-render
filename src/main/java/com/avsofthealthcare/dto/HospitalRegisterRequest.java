package com.avsofthealthcare.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class HospitalRegisterRequest {
    private String email;
    private String phone;
    private String password;
    private String confirmPassword;

    private String hospitalName;
    private String headCeoName;
    private String hospitalregistrationNumber;
    private String location;
    private String hospitalType;
    private String hospitalgstNumber;

    private boolean inHouseLab;
    private String labLicenseNo;
    private boolean inHousePharmacy;
    private String pharmacyLicenseNo;
    private boolean agreeDeclaration;

    private MultipartFile nabhCertificate;
}