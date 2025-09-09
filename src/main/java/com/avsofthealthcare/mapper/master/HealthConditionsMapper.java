package com.avsofthealthcare.mapper.master;


import com.avsofthealthcare.dto.master.HealthConditionsRequestDto;
import com.avsofthealthcare.dto.master.HealthConditionsResponseDto;
import com.avsofthealthcare.entity.master.HealthConditions;

public class HealthConditionsMapper {

    public static HealthConditions toEntity(HealthConditionsRequestDto dto) {
        return HealthConditions.builder()
                .healthConditionName(dto.getHealthConditionName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static HealthConditionsResponseDto toDto(HealthConditions gender) {
        HealthConditionsResponseDto dto = new HealthConditionsResponseDto();
        dto.setId(gender.getId());
        dto.setHealthConditionName(gender.getHealthConditionName());
        dto.setDescription(gender.getDescription());
        dto.setActive(gender.getActive());

        dto.setCreatedBy(gender.getCreatedBy());
        dto.setUpdatedBy(gender.getUpdatedBy());
        dto.setCreatedAt(gender.getCreatedAt());
        dto.setUpdatedAt(gender.getUpdatedAt());
        return dto;
    }
}