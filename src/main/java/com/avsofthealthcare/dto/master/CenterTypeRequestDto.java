package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CenterTypeRequestDto {
    @NotBlank(message = "centerTypeName is required")
    private String centerTypeName;
    @NotBlank(message = "description is required")
    private String description;
    private Boolean active; // Optional in request, defaults to true
}
