package com.avsofthealthcare.dto.dashboard.patientdashboard;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AdditionalDetailsRequestDTO {
    @NotBlank(message = "insurance provider name is required")
	private String insuranceProviderName;
    @NotBlank(message = "policynum is required")
	private String policyNum;
    @NotNull(message = "coveragetypeid is required")
	private Integer coverageTypeId;
    @Positive(message = "coverageamount should be greater than 0")
	private double coverageAmount;

	private boolean isPrimaryHolder;
    @NotNull(message = "policystartdate is required")
	private LocalDate policyStartDate;
    @NotNull(message = "policyenddate is required")
    @FutureOrPresent(message = "policyenddate cant be in past")
	private LocalDate policyEndDate;
}

