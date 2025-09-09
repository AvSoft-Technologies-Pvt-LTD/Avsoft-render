package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.MedicalConditionsRequestDto;
import com.avsofthealthcare.dto.master.MedicalConditionsResponseDto;
import com.avsofthealthcare.entity.master.MedicalConditions;

public class MedicalConditionsMapper {

    public static MedicalConditions toEntity(MedicalConditionsRequestDto dto) {
        return MedicalConditions.builder()
                .conditionName(dto.getConditionName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static MedicalConditionsResponseDto toDto(MedicalConditions gender) {
        MedicalConditionsResponseDto dto = new MedicalConditionsResponseDto();
        dto.setId(gender.getId());
        dto.setConditionName(gender.getConditionName());
        dto.setDescription(gender.getDescription());
        dto.setActive(gender.getActive());

        dto.setCreatedBy(gender.getCreatedBy());
        dto.setUpdatedBy(gender.getUpdatedBy());
        dto.setCreatedAt(gender.getCreatedAt());
        dto.setUpdatedAt(gender.getUpdatedAt());
        return dto;
    }
}
