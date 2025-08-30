package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpecialServicesRequestDto {
    @NotBlank(message ="serviceName is required" )
    private String serviceName;
    @NotBlank(message ="description is required" )
    private String description;
    private Boolean active; // Optional in request, defaults to true
}

