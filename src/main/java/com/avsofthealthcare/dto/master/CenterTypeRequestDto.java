package com.avsofthealthcare.dto.master;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CenterTypeRequestDto {
    @NotBlank
    private String centerTypeName;
    private String description;
    private Boolean active; // Optional in request, defaults to true
}
