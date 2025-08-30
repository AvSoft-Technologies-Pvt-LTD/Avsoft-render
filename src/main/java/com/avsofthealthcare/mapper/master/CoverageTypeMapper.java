package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.CoverageTypeRequestDto;
import com.avsofthealthcare.dto.master.CoverageTypeResponseDto;
import com.avsofthealthcare.entity.master.CoverageType;

public class CoverageTypeMapper {

    public static CoverageType toEntity(CoverageTypeRequestDto dto) {
        return CoverageType.builder()
                .coverageTypeName(dto.getCoverageTypeName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static CoverageTypeResponseDto toDto(CoverageType coverageType) {
        CoverageTypeResponseDto dto = new CoverageTypeResponseDto();
        dto.setId(coverageType.getId());
        dto.setCoverageTypeName(coverageType.getCoverageTypeName());
        dto.setDescription(coverageType.getDescription());
        dto.setActive(coverageType.getActive());

        dto.setCreatedBy(coverageType.getCreatedBy());
        dto.setUpdatedBy(coverageType.getUpdatedBy());
        dto.setCreatedAt(coverageType.getCreatedAt());
        dto.setUpdatedAt(coverageType.getUpdatedAt());
        return dto;
    }
}
