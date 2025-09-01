package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MedicalConditionsRequestDto {
    @NotBlank
    private String conditionName;
    private String description;
    private Boolean active; // Optional in request, defaults to true
}
