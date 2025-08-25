package com.avsofthealthcare.dto.master;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalConditionsResponseDto {
    private Integer id;
    private String conditionName;
    private String description;
    private Boolean active;


    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
