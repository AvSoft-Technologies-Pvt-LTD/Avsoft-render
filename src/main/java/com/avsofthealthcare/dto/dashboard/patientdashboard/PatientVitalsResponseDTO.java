package com.avsofthealthcare.dto.dashboard.patientdashboard;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PatientVitalsResponseDTO {
	private Long id;
	private String patientId;
	private Integer heartRate;
	private Double temperature;
	private Double bloodSugar;
	private String bloodPressure;
	private Integer respiratoryRate;
	private Integer spo2;
	private Integer steps;

	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;
}
