package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CertificateTypesRequestDto {
    @NotBlank(message = "certificateName is required")
    private String certificateName;
    @NotBlank(message = "description is required")
    private String description;
    private Boolean active; // Optional in request, defaults to true
}
