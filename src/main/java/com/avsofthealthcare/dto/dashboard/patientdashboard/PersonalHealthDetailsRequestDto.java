package com.avsofthealthcare.dto.dashboard.patientdashboard;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PersonalHealthDetailsRequestDto {

    @NotBlank
    private String patientId;


    private Double height;

    private Double weight;

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
