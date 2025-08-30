package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HospitalTypeRequestDto {
    @NotBlank(message ="hospitalTypeName is required" )
    private String hospitalTypeName;
    @NotBlank(message ="description is required" )
    private String description;
    private Boolean active; // Optional in request, defaults to true
}
