package com.avsofthealthcare.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SurgeryRequestDTO {
    private String surgeryName;
    private LocalDate surgeryDate;
    private String notes;
}
