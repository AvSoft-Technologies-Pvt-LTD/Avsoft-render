package com.avsofthealthcare.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OpdMedicalRecordRequestDTO {
    private Long userId;
    private Long hospitalId;
    private Long medicalConditionId;
    private String chiefComplaint;
    private String status;
    private LocalDate dateOfVisit;
}
