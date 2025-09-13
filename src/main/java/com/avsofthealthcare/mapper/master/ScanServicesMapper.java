package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.ScanServicesRequestDto;
import com.avsofthealthcare.dto.master.ScanServicesResponseDto;
import com.avsofthealthcare.entity.master.ScanServices;

public class ScanServicesMapper {

    public static ScanServices toEntity(ScanServicesRequestDto dto) {
        return ScanServices.builder()
                .scanName(dto.getScanName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static ScanServicesResponseDto toDto(ScanServices gender) {
        ScanServicesResponseDto dto = new ScanServicesResponseDto();
        dto.setId(gender.getId());
        dto.setScanName(gender.getScanName());
        dto.setDescription(gender.getDescription());
        dto.setActive(gender.getActive());

        dto.setCreatedBy(gender.getCreatedBy());
        dto.setUpdatedBy(gender.getUpdatedBy());
        dto.setCreatedAt(gender.getCreatedAt());
        dto.setUpdatedAt(gender.getUpdatedAt());
        return dto;
    }
}

