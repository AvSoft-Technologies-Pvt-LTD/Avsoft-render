package com.avsofthealthcare.dto;


import com.avsofthealthcare.dto.master.PasswordMatches;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@PasswordMatches
@Data
public class PatientRegisterRequest {
    @NotBlank(message = "firstName is required")
    private String firstName;
    private String middleName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @NotBlank(message = "phno is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "phno must be a 10 digit number")
    private String phone;
    @NotBlank(message = "email is required")
    @Email(message = "enter a valid email")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 8,max = 20,message = "password must be between 8 and 20 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&+=]).{8,20}$",
            message = "password must contain at least one digit, one lowercase, one uppercase, and a special character"
    )
    private String password;
    @NotBlank(message = "please confirm your enterd password")
    private String confirmPassword;
    @NotBlank(message = "aadhar is required")
    @NotBlank(message = "aadhar is required")
    @Pattern(regexp = "^[0-9]{12}$",message = "adhar must be a 12 digit number")
    private String aadhaar;
    @NotNull(message = "genderId is required")
    private Integer genderId;  // used for input
    @NotNull(message = "dob is required")
    @Past(message = "dob must be in past")
    private LocalDate dob;
    @NotBlank(message = "occupation is required")
    private String occupation;
    @NotBlank(message = "pincode is required")
    @Pattern(regexp = "^[1-9][0-9]{5}",message = "pincode must be valid 6 digit number")
    private String pinCode;
    @NotBlank(message = "city is required")
    @Pattern(regexp = "^[A-Za-z ]+$",message = "city must include only letters")
    private String city;
    @NotBlank(message = "district is required")
    @Pattern(regexp = "^[A-Za-z ]+$",message = "district must include only letters")
    private String district;
    @NotBlank(message = "state is required")
    @Pattern(regexp = "^[A-Za-z ]+$",message = "state must include only letters")
    private String state;
//    private String temporaryAddress;
//    private String permanentAddress;
//    private boolean sameAsPermanent;
    private boolean agreeDeclaration;


    private MultipartFile photo;
}
