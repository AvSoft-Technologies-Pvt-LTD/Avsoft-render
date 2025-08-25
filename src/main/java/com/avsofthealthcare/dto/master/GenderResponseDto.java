package com.avsofthealthcare.dto.master;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GenderResponseDto {
    private Integer id;
    private String genderName;
    private String description;
    private Boolean active;


    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
