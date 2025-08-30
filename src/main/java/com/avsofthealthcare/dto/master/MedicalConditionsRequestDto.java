package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MedicalConditionsRequestDto {
    @NotBlank(message ="conditionName is required" )
    private String conditionName;
    @NotBlank(message ="description is required" )
    private String description;
    private Boolean active; // Optional in request, defaults to true
}
