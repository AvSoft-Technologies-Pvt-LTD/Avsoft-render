package com.avsofthealthcare.dto.dashboard.patientdashboard;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PersonalHealthDetailsResponseDto {

    private Long id;
    private String patientId;
    private Double height;
    private Double weight;

    private Integer bloodGroupId;
    private String bloodGroupName;

    private String surgeries;
    private String allergies;

    private Boolean isSmoker;
    private Integer yearsSmoking;

    private Boolean isAlcoholic;
    private Integer yearsAlcoholic;

    private Boolean isTobacco;
    private Integer yearsTobacco;

    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
}
