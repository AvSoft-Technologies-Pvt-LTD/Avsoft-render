package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lab_tests")
@Getter
@Setter
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private String code;      // e.g. CBC001
    private String testname;
    private String testType;
    private String description;
    private double price;
    private String labName;   // HealthPlus Diagnostics, Apollo, etc.
}
