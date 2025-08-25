package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CertificateTypesRequestDto {
    @NotBlank
    private String certificateName;
    private String description;
    private Boolean active; // Optional in request, defaults to true
}
