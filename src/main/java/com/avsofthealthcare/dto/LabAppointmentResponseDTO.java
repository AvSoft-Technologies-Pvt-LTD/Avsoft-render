package com.avsofthealthcare.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class LabAppointmentResponseDTO {
    private String appointmentId;
    private String patientId;
    private LocalDate appointmentDate;
    private String status;
    private List<String> tests;
    private List<String> scans;
    private double totalAmount;
}
