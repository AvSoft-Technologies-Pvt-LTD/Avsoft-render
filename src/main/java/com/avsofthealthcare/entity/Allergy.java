package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "allergies")
@Data
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String allergyName;

    private String severity;   // e.g., Mild, Moderate, Severe

    private String notes;

    private LocalDate diagnosedDate;
}
