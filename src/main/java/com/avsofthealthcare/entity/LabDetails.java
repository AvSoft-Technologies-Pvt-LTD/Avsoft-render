package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lab_details")
@Data
public class LabDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String userrole;

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

    private String certificates;

    private String certificateTypes;

    private String password;
    private String confirmPassword;

    private boolean agreeDeclaration;
}