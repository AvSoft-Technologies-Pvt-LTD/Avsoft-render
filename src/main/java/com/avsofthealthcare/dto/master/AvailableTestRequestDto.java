package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AvailableTestRequestDto {
    @NotBlank
    private String testName;
    private String description;
    private Boolean active; // Optional in request, defaults to true
}
