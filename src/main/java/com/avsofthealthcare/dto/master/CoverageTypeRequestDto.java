package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CoverageTypeRequestDto {
    @NotBlank(message ="coverageTypeName is required" )
    private String coverageTypeName;
    @NotBlank(message ="description is required" )
    private String description;
    private Boolean active; // Optional in request, defaults to true
}
