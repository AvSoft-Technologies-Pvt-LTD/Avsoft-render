package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.SpecializationRequestDto;
import com.avsofthealthcare.dto.master.SpecializationResponseDto;
import com.avsofthealthcare.entity.master.PracticeType;
import com.avsofthealthcare.entity.master.Specialization;

public class SpecializationMapper {

    public static Specialization toEntity(SpecializationRequestDto dto, PracticeType practiceType) {
        return Specialization.builder()
                .specializationName(dto.getSpecializationName())
                .description(dto.getDescription())
		        .symptoms(dto.getSymptoms()) // ✅ Map symptoms
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .practiceType(practiceType)
                .build();
    }

    public static SpecializationResponseDto toDto(Specialization entity) {
        SpecializationResponseDto dto = new SpecializationResponseDto();
        dto.setId(entity.getId());
        dto.setSpecializationName(entity.getSpecializationName());
        dto.setDescription(entity.getDescription());
	    dto.setSymptoms(entity.getSymptoms()); // ✅ Map symptoms
        dto.setActive(entity.getActive());

        if (entity.getPracticeType() != null) {
            dto.setPracticeTypeId(entity.getPracticeType().getId());
            dto.setPracticeTypeName(entity.getPracticeType().getPracticeName());
        }

        dto.setCreatedBy(entity.getCreatedBy());
        dto.setUpdatedBy(entity.getUpdatedBy());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}