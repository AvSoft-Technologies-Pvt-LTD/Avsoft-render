package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpecialServicesRequestDto {
    @NotBlank
    private String serviceName;
    private String description;
    private Boolean active; // Optional in request, defaults to true
}

