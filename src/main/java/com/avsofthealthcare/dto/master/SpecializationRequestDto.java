package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SpecializationRequestDto {

    @NotBlank
    private String specializationName;

    private String description;

    @NotNull(message = "Practice Type ID is required")
    private Integer practiceTypeId;

    private Boolean active = true; // Optional, default to true if not provided


}