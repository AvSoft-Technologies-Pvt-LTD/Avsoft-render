package com.avsofthealthcare.dto.dashboard.patientdashboard;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdditionalDetailsRequestDTO {
	private String insuranceProviderName;
	private String policyNum;
	private Integer coverageTypeId;
	private double coverageAmount;
	private boolean isPrimaryHolder;
	private LocalDate policyStartDate;
	private LocalDate policyEndDate;
}

