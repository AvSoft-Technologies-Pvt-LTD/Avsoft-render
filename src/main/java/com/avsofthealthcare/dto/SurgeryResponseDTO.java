package com.avsofthealthcare.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SurgeryResponseDTO {
    private Long id;
    private Long userId;
    private String surgeryName;
    private LocalDate surgeryDate;
    private String notes;
}
