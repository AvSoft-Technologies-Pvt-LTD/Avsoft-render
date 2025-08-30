package com.avsofthealthcare.dto.dashboard.patientdashboard;

import com.avsofthealthcare.dto.master.HealthConditionDropdownDto;
import com.avsofthealthcare.dto.master.HealthConditionsRequestDto;
import com.avsofthealthcare.dto.master.HealthConditionsResponseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FamilyMemberResponseDTO {
    private Long id;
    private String patientId;

    private Integer relationId;
    private String relationName; // from relation.relationName
    private String relationDescription; // optional if you want to show description

    private String memberName;
    private String phoneNumber;

    private List<HealthConditionDropdownDto> healthConditions;

    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
}
