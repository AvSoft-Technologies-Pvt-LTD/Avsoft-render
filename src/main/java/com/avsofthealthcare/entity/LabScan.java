package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lab_scans")
@Getter
@Setter
public class LabScan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private String code;      // e.g. MRI001
    private String scanname;
    private String scanType;
    private String description;
    private double price;
    private String labName;   // Apollo, Fortis, etc.
}
