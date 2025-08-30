package com.avsofthealthcare.dto;

import com.avsofthealthcare.dto.master.PasswordMatches;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@PasswordMatches
public class HospitalRegisterRequest {
    @NotBlank(message = "email is required")
    @Email(message = "email is invalid")
    private String email;
    @NotBlank(message = "phonno is required ")
    @Pattern(regexp = "^[0-9]{10}$",message = "phoneno must be 10 digit number")
    private String phone;
    @NotBlank(message = "password is required")
    @Size(min=8,max =20,message = "password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!]).*$", message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character"
    )
    private String password;
    @NotBlank(message = "please  confirm your entered password")
    private String confirmPassword;
    @NotBlank(message ="hospitalName is required" )
    private String hospitalName;
    @NotBlank(message = "headCeoName is required")
    private String headCeoName;
    @NotBlank(message = "hospitalregistrationNumber is required")
    private String hospitalregistrationNumber;
    @NotBlank(message = "location is required")
    @Pattern(regexp = "^[A-Za-z ]+$",message = "location must contain only letters and spaces")
    private String location;
    @NotBlank(message ="hospitalType is required" )
    private String hospitalType;
    @NotBlank(message = "hospitalgstNumber is required")
    private String hospitalgstNumber;

    private boolean inHouseLab;
    @NotBlank(message = "labLicenseNo is required")
    private String labLicenseNo;
    private boolean inHousePharmacy;
    private String pharmacyLicenseNo;
    private boolean agreeDeclaration;
    @NotNull(message = "nabhCertificate is required")
    private MultipartFile nabhCertificate;
}