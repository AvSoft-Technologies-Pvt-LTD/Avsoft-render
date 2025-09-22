package com.avsofthealthcare.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class IpdMedicalRecordResponseDTO {
    private Long recordId;
    private Long userId;
    private String hospitalName;
    private String medicalConditionName;
    private String chiefComplaint;
    private String status;
    private LocalDate dateOfAdmission;
    private LocalDate dateOfDischarge;
}
