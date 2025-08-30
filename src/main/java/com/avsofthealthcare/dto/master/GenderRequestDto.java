package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GenderRequestDto {
    @NotBlank(message ="genderName is required" )
    private String genderName;
    @NotBlank(message ="description is required" )
    private String description;
    private Boolean active; // Optional in request, defaults to true
}

