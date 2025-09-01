package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CoverageTypeRequestDto {
    @NotBlank
    private String coverageTypeName;
    private String description;
    private Boolean active; // Optional in request, defaults to true
}
