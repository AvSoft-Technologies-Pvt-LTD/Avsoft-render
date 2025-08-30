package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PracticeTypeRequestDto {
    @NotBlank(message ="practiceName is required" )
    private String practiceName;
    @NotBlank(message ="description is required" )
    private String description;
    private Boolean active; // Optional in request, defaults to true
}

