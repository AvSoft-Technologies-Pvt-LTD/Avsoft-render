package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BloodGroupRequestDto {
    @NotBlank(message = "bloodGroupName is required")
    private String bloodGroupName;
    @NotBlank(message = "description is required")
    private String description;
    private Boolean active; // Optional in request, defaults to true
}

