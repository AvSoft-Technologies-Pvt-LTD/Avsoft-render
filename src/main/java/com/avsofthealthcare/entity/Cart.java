package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "lab_cart")
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    @ManyToMany
    @JoinTable(
            name = "cart_tests",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private List<LabTest> tests;

    @ManyToMany
    @JoinTable(
            name = "cart_scans",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "scan_id")
    )
    private List<LabScan> scans;
}
