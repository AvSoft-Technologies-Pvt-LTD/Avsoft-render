package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.HospitalTypeRequestDto;
import com.avsofthealthcare.dto.master.HospitalTypeResponseDto;
import com.avsofthealthcare.entity.master.HospitalType;

public class HospitalTypeMapper {

    public static HospitalType toEntity(HospitalTypeRequestDto dto) {
        return HospitalType.builder()
                .hospitalTypeName(dto.getHospitalTypeName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static HospitalTypeResponseDto toDto(HospitalType gender) {
        HospitalTypeResponseDto dto = new HospitalTypeResponseDto();
        dto.setId(gender.getId());
        dto.setHospitalTypeName(gender.getHospitalTypeName());
        dto.setDescription(gender.getDescription());
        dto.setActive(gender.getActive());

        dto.setCreatedBy(gender.getCreatedBy());
        dto.setUpdatedBy(gender.getUpdatedBy());
        dto.setCreatedAt(gender.getCreatedAt());
        dto.setUpdatedAt(gender.getUpdatedAt());
        return dto;
    }
}