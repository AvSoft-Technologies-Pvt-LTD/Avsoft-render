package com.avsofthealthcare.dto.dashboard.patientdashboard;

import lombok.Data;

import java.util.List;

@Data
public class FamilyMemberRequestDTO {
    private String patientId;
    private Long relationId;
    private String memberName;
    private String phoneNumber;
    private List<Long> healthConditionIds;
}
