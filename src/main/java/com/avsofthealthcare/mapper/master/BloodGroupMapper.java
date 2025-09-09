package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.BloodGroupRequestDto;
import com.avsofthealthcare.dto.master.BloodGroupResponseDto;
import com.avsofthealthcare.entity.master.BloodGroup;

public class BloodGroupMapper {

    public static BloodGroup toEntity(BloodGroupRequestDto dto) {
        return BloodGroup.builder()
                .bloodGroupName(dto.getBloodGroupName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static BloodGroupResponseDto toDto(BloodGroup bloodGroup) {
        BloodGroupResponseDto dto = new BloodGroupResponseDto();
        dto.setId(bloodGroup.getId());
        dto.setBloodGroupName(bloodGroup.getBloodGroupName());
        dto.setDescription(bloodGroup.getDescription());
        dto.setActive(bloodGroup.getActive());

        dto.setCreatedBy(bloodGroup.getCreatedBy());
        dto.setUpdatedBy(bloodGroup.getUpdatedBy());
        dto.setCreatedAt(bloodGroup.getCreatedAt());
        dto.setUpdatedAt(bloodGroup.getUpdatedAt());
        return dto;
    }
}
