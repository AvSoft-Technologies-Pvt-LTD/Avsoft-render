package com.avsofthealthcare.dto;

import com.avsofthealthcare.dto.master.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@PasswordMatches
public class LabRegisterRequest {
    @NotBlank(message = "email is required")
    @Email(message = "invalid email format")
    private String email;
    @NotBlank(message = "phone no is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "phone no should be 10 digit no")
    private String phone;
    @NotBlank(message = "password is required")
    @Size(min=8,max=20,message = "password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!]).*$",message = "password must contain atleast one digit,one lowercase,one uppercase ,and one special character")
    private String password;
    @NotBlank(message = "please confirm your entered password")
    private String confirmPassword;
    @NotBlank(message = "centertype is required")
    private String centerType;
    @NotBlank(message = "centername is required")
    private String centerName;
    @NotBlank(message = "ownerFullName is required")
    private String ownerFullName;

    private String availableTest;
    @NotBlank(message = "labregistrationNumber is required")
    private String labregistrationNumber;

    private String scanServices;
    private String specialServices;
    @NotBlank(message = "licenseNumber is required")
    private String licenseNumber;
    @NotBlank(message = "location is required")
    private String lablocation;
    @NotBlank(message = "labgstNumber is required")
    private String labgstNumber;
    private String certificateTypes;

    private boolean agreeDeclaration;

    private MultipartFile certificates;
}
