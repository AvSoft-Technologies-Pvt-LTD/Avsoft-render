package com.avsofthealthcare.avsofthealthcare.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

@Entity
@Table(name = "doctor_details")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String userrole;

    @NotBlank(message = "First name is required")
    private String firstName;

    // Optional field
    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Pattern(regexp = "^[0-9+]{10,13}$", message = "Phone number must be 10-13 digits")
    private String phone;

    // Optional field - no validation required
    private String aadhaar;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Column(name = "dob")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @Email(message = "Invalid email format")
    private String email;

    // Optional field
    private String photo;

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    @NotBlank(message = "Practice type is required")
    private String practiceType;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    // Optional field
    private String qualification;

    // Optional field
    private String location;

    @Column(columnDefinition = "TEXT")
    // Optional field
    private String documents;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String confirmPassword;

    @NotNull(message = "Agreement declaration is required")
    private boolean agreeDeclaration;


}