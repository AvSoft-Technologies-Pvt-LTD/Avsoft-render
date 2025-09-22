package com.avsofthealthcare.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class OpdMedicalRecordResponseDTO {
    private Long recordId;
    private Long userId;
    private String hospitalName;
    private String chiefComplaint;
    private String medicalConditionName;
    private String status;
    private LocalDate dateOfVisit;
}
