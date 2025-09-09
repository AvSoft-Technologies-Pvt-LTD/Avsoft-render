package com.avsofthealthcare.dto.dashboard.patientdashboard;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AdditionalDetailsResponseDTO {

	private Long id;
	private String patientId;

	private String insuranceProviderName;
	private String policyNum;
	private String coverageTypeName;
	private Integer coverageTypeId;
	private double coverageAmount;
	private boolean isPrimaryHolder;
	private LocalDate policyStartDate;
	private LocalDate policyEndDate;


	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;

}
