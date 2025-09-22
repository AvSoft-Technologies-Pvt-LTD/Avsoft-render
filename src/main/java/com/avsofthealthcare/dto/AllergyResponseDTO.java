package com.avsofthealthcare.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AllergyResponseDTO {
    private Long id;
    private Long userId;
    private String allergyName;
    private String severity;
    private String notes;
    private LocalDate diagnosedDate;
}
