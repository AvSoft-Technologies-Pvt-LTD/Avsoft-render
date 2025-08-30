package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AvailableTestRequestDto {
    @NotBlank(message = "testName is required")
    private String testName;
    @NotBlank(message = "description is required")
    private String description;
    private Boolean active; // Optional in request, defaults to true
}
