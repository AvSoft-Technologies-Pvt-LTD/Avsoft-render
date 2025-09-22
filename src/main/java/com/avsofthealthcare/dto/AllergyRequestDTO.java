package com.avsofthealthcare.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AllergyRequestDTO {
    private String allergyName;
    private String severity;
    private String notes;
    private LocalDate diagnosedDate;
}
