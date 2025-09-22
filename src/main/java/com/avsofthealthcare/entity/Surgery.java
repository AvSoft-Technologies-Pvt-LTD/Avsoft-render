package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "surgeries")
@Data
public class Surgery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String surgeryName;

    private LocalDate surgeryDate;

    private String notes;
}
