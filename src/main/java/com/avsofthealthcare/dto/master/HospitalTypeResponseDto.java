package com.avsofthealthcare.dto.master;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HospitalTypeResponseDto {
    private Integer id;
    private String hospitalTypeName;
    private String description;
    private Boolean active;


    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
