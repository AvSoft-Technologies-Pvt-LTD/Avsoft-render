package com.avsofthealthcare.dto.dashboard.patientdashboard;

import lombok.Data;

@Data
public class PatientVitalsRequestDTO {
	private String patientId;
	private Integer heartRate;
	private Double temperature;
	private Double bloodSugar;
	private String bloodPressure;   // keep as "120/80" or change to two integers if needed
	private Integer respiratoryRate;
	private Integer spo2;
	private Integer steps;
}
