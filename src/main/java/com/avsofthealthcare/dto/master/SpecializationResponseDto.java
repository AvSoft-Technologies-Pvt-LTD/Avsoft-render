package com.avsofthealthcare.dto.master;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SpecializationResponseDto {

    private Integer id;
    private String specializationName;
    private String description;
    // PracticeType info
    private Integer practiceTypeId;
    private String practiceTypeName;

    private Boolean active;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

