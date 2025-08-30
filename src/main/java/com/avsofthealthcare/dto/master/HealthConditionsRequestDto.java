package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HealthConditionsRequestDto {
    @NotBlank(message ="healthConditionName is required" )
    private String healthConditionName;
    @NotBlank(message ="description is required" )
    private String description;
    private Boolean active; // Optional in request, defaults to true
}

