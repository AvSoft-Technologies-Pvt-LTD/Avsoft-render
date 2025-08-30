package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.CenterTypeRequestDto;
import com.avsofthealthcare.dto.master.CenterTypeResponseDto;
import com.avsofthealthcare.entity.master.CenterType;

public class CenterTypeMapper {

    public static CenterType toEntity(CenterTypeRequestDto dto) {
        return CenterType.builder()
                .centerTypeName(dto.getCenterTypeName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static CenterTypeResponseDto toDto(CenterType bloodGroup) {
        CenterTypeResponseDto dto = new CenterTypeResponseDto();
        dto.setId(bloodGroup.getId());
        dto.setCenterTypeName(bloodGroup.getCenterTypeName());
        dto.setDescription(bloodGroup.getDescription());
        dto.setActive(bloodGroup.getActive());

        dto.setCreatedBy(bloodGroup.getCreatedBy());
        dto.setUpdatedBy(bloodGroup.getUpdatedBy());
        dto.setCreatedAt(bloodGroup.getCreatedAt());
        dto.setUpdatedAt(bloodGroup.getUpdatedAt());
        return dto;
    }
}
