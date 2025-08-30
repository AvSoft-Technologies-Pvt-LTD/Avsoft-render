package com.avsofthealthcare.dto.dashboard.patientdashboard;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class FamilyMemberRequestDTO {
    @NotBlank(message = "patientId is required")
    private String patientId;
    @NotNull(message = "relationId is required")
    private Long relationId;
    @NotBlank(message = "memberName should not be empty")
    private String memberName;
    @NotBlank(message = "phoneno is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "phono must be 10 digits")
    private String phoneNumber;
    @NotNull(message = "healthCondition is required")
    @Size(min = 1,message = "Atleast one healthcondition must be selected")
    private List<@NotNull @Positive Long> healthConditionIds;
}
