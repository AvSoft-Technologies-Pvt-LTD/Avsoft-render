package com.avsofthealthcare.mapper.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.FamilyMemberResponseDTO;
import com.avsofthealthcare.dto.master.HealthConditionDropdownDto;
import com.avsofthealthcare.dto.master.HealthConditionsRequestDto;
import com.avsofthealthcare.dto.master.HealthConditionsResponseDto;
import com.avsofthealthcare.entity.dashboard.patientdashboard.FamilyMemberDetails;
import com.avsofthealthcare.entity.master.HealthConditions;

import java.util.List;
import java.util.stream.Collectors;

public class FamilyMemberMapper {

    public static FamilyMemberResponseDTO toDTO(FamilyMemberDetails member) {
        FamilyMemberResponseDTO dto = new FamilyMemberResponseDTO();
        dto.setId(member.getId());
        dto.setPatientId(member.getPatientId());

        if (member.getRelation() != null) {
            dto.setRelationId(member.getRelation().getId());
            dto.setRelationName(member.getRelation().getRelationName());
            dto.setRelationDescription(member.getRelation().getDescription());
        }

        dto.setMemberName(member.getMemberName());
        dto.setPhoneNumber(member.getPhoneNumber());

        dto.setCreatedBy(member.getCreatedBy());
        dto.setCreatedAt(member.getCreatedAt());
        dto.setUpdatedBy(member.getUpdatedBy());
        dto.setUpdatedAt(member.getUpdatedAt());

        if (member.getHealthConditions() != null) {
            List<HealthConditionDropdownDto> healthDTOs = member.getHealthConditions()
                    .stream()
                    .map(hc -> {
                        HealthConditions condition = hc.getHealthCondition();
                        return HealthConditionDropdownDto.builder()
                                .id(condition.getId())
                                .healthConditionName(condition.getHealthConditionName())
                                .build();
                    })
                    .collect(Collectors.toList());
            dto.setHealthConditions(healthDTOs);
        }

        return dto;
    }

}

