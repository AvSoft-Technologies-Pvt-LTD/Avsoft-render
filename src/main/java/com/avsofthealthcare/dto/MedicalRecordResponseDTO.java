// DTO for response
package com.avsofthealthcare.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MedicalRecordResponseDTO {
    private Long recordId;
    private Long userId;
    private String hospitalName;
    private String medicalConditionName;
    private String chiefComplaint;
    private String status;
    private String type;
    private LocalDate dateOfConsultation;
}
