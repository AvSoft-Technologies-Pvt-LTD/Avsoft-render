package com.avsofthealthcare.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "pateint_details")
@Data
public class PatientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String userrole;

    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String aadhaar;
    private String gender;

    @Column(name = "Patient_dob")
    private LocalDate Patient_dob;

    private String email;
    private String occupation;

    @Column(columnDefinition = "TEXT")
    private String temporaryAddress;

    @Column(columnDefinition = "TEXT")
    private String permanentAddress;

    private String photo;

    private boolean isSameAsPermanent;

    private String password;
    private String confirmPassword;

    private boolean agreeDeclaration;

    public void setDob(LocalDate pdob) {

    }
}