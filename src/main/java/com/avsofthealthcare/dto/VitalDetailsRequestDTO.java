package com.avsofthealthcare.dto;

import lombok.Data;

@Data
public class VitalDetailsRequestDTO {
    private Integer heartRate;
    private Double temperature;
    private Integer bloodSugar;
    private String bloodPressure;
    private Integer respiratoryRate;
    private Integer spO2;
    private Integer steps;

    private Long patientId; // ✅ new field
}
