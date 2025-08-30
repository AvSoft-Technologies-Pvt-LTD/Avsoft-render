package com.avsofthealthcare.dto;

import com.avsofthealthcare.dto.master.PasswordMatches;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@PasswordMatches
public class DoctorRegisterRequest {
    @NotBlank(message = "firstName is required")
	private String firstName;
	private String middleName;
    @NotBlank(message = "lastName is required")
	private String lastName;
    @NotBlank(message = "phone is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "phone no must be 10 digits")
	private String phone;
    @NotBlank(message = "email is required")
    @Email(message = "invalid email format")
	private String email;
    @NotBlank(message = "aadhar is required")
    @Pattern(regexp = "^[0-9]{12}$",message = "aadhar must be 12 digit number")
	private String aadhaar;
    @NotNull(message = "genderid is required")
    @Positive(message = "genderid must be positive number")
	private Integer genderId;         // FK → Gender
    @NotNull(message = "dob is required")
    @Past(message = "dob must be in past")
	private LocalDate dob;
    @NotBlank(message = "registrationNumber is required")
	private String registrationNumber;
    @NotNull(message = "practiceTypeId is required")
	private Integer practiceTypeId;   // FK → PracticeType
    @NotNull(message = "specializationId is required")
	private Integer specializationId; // FK → Specialization
    @NotBlank(message = "qualification is required")
	private String qualification;
    @NotBlank(message = "pincode is required")
    @Pattern(regexp = "^[1-9][0-9]{5}$",message = "pincode must be valid 6 digit number")
	private String pincode;
    @NotBlank(message = "city is required")
    @Pattern(regexp = "^[A-Za-z ]+$",message = "city must include only letters ")
	private String city;
    @NotBlank(message = "district is required")
    @Pattern(regexp = "^[A-Za-z ]+$",message = "district must contain only letters")
	private String district;
    @NotBlank(message = "state is required")
    @Pattern(regexp = "^[A-Za-z ]+$",message = "state must contain only letters")
	private String state;
    @NotBlank(message = "password is required")
    @Size(min = 8,max = 20,message = "password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!]).*$",
            message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character"
            )
	private String password;
    @NotBlank(message = "you have to confirm entered password ")
	private String confirmPassword;

	private boolean agreeDeclaration;
    @NotNull(message = "photo must be uploaded")
	private MultipartFile photo;
}
