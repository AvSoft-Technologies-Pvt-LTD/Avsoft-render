package com.avsofthealthcare.mapper.master;


import com.avsofthealthcare.dto.master.GenderRequestDto;
import com.avsofthealthcare.dto.master.GenderResponseDto;
import com.avsofthealthcare.entity.master.Gender;

public class GenderMapper {

    public static Gender toEntity(GenderRequestDto dto) {
        return Gender.builder()
                .genderName(dto.getGenderName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static GenderResponseDto toDto(Gender gender) {
        GenderResponseDto dto = new GenderResponseDto();
        dto.setId(gender.getId());
        dto.setGenderName(gender.getGenderName());
        dto.setDescription(gender.getDescription());
        dto.setActive(gender.getActive());

        dto.setCreatedBy(gender.getCreatedBy());
        dto.setUpdatedBy(gender.getUpdatedBy());
        dto.setCreatedAt(gender.getCreatedAt());
        dto.setUpdatedAt(gender.getUpdatedAt());
        return dto;
    }
}