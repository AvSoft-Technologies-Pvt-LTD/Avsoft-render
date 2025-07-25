package com.avsofthealthcare.avsofthealthcare.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"photo", "documents"})
public class DoctorRegisterRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    private String aadhaar;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @NotBlank(message = "Practice type is required")
    private String practiceType;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    private String qualification;
    private String location;
    private boolean agreeDeclaration;
    
    private transient MultipartFile photo;
    private transient List<MultipartFile> documents;

    public void setDob(String dob) {
        if (dob != null && !dob.trim().isEmpty()) {
            this.dob = LocalDate.parse(dob);
        }
    }
}