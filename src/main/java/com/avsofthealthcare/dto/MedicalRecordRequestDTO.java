// DTO for creating/updating medical record
package com.avsofthealthcare.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicalRecordRequestDTO {
    private Long userId;
    private Long hospitalId;
    private Long medicalConditionId;
    private String chiefComplaint;
    private String status;
    private String type; // "OPD", "IPD", "VIRTUAL"
    private LocalDate dateOfConsultation; // can be admission/discharge for IPD too
}

