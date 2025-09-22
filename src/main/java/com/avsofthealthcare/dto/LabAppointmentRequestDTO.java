package com.avsofthealthcare.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class LabAppointmentRequestDTO {
    private String patientId;
    private LocalDate appointmentDate;
    private List<Long> testIds;   // Can be empty/null
    private List<Long> scanIds;   // Can be empty/null
}
