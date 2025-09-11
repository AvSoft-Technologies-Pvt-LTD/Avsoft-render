package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vital_details")
public class VitalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer heartRate;
    private Double temperature;
    private Integer bloodSugar;
    private String bloodPressure;
    private Integer respiratoryRate;
    private Integer spO2;
    private Integer steps;
}
