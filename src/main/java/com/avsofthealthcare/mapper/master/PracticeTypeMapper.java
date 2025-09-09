package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.PracticeTypeRequestDto;
import com.avsofthealthcare.dto.master.PracticeTypeResponseDto;
import com.avsofthealthcare.entity.master.PracticeType;

public class PracticeTypeMapper {

    public static PracticeType toEntity(PracticeTypeRequestDto dto) {
        return PracticeType.builder()
                .practiceName(dto.getPracticeName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static PracticeTypeResponseDto toDto(PracticeType gender) {
        PracticeTypeResponseDto dto = new PracticeTypeResponseDto();
        dto.setId(gender.getId());
        dto.setPracticeName(gender.getPracticeName());
        dto.setDescription(gender.getDescription());
        dto.setActive(gender.getActive());

        dto.setCreatedBy(gender.getCreatedBy());
        dto.setUpdatedBy(gender.getUpdatedBy());
        dto.setCreatedAt(gender.getCreatedAt());
        dto.setUpdatedAt(gender.getUpdatedAt());
        return dto;
    }
}


