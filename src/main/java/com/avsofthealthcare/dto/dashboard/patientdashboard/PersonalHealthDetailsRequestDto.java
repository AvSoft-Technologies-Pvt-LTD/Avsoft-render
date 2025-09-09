package com.avsofthealthcare.dto.dashboard.patientdashboard;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PersonalHealthDetailsRequestDto {

	@NotBlank(message = "patientId is required")
	private String patientId;


	private Double height;

	private Double weight;

	@NotNull(message = "bloodGroupId is required")
	private Integer bloodGroupId; // ID of the selected BloodGroup

	private String surgeries;

	private String allergies;

	private Boolean isSmoker;

	private Integer yearsSmoking;

	private Boolean isAlcoholic;

	private Integer yearsAlcoholic;

	private Boolean isTobacco;

	private Integer yearsTobacco;
}
