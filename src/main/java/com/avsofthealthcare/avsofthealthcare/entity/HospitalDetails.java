package com.avsofthealthcare.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hospital_details")
@Data
public class HospitalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private String userrole;

    private String hospitalName;
    private String headCeoName;
    private String hospitalregistrationNumber;
    private String location;
    private String hospitalType;
    private String hospitalgstNumber;

    private String nabhCertificate;

    private boolean inHouseLab;
    private String labLicenseNo;
    private boolean inHousePharmacy;
    private String pharmacyLicenseNo;

    private String password;
    private String confirmPassword;

    private boolean agreeDeclaration;
}
