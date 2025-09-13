package com.avsofthealthcare.dto.master;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CenterTypeResponseDto {
    private Integer id;
    private String centerTypeName;
    private String description;
    private Boolean active;


    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

