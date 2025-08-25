package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.SpecialServicesRequestDto;
import com.avsofthealthcare.dto.master.SpecialServicesResponseDto;
import com.avsofthealthcare.entity.master.SpecialServices;

public class SpecialServicesMapper {

    public static SpecialServices toEntity(SpecialServicesRequestDto dto) {
        return SpecialServices.builder()
                .serviceName(dto.getServiceName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static SpecialServicesResponseDto toDto(SpecialServices gender) {
        SpecialServicesResponseDto dto = new SpecialServicesResponseDto();
        dto.setId(gender.getId());
        dto.setServiceName(gender.getServiceName());
        dto.setDescription(gender.getDescription());
        dto.setActive(gender.getActive());

        dto.setCreatedBy(gender.getCreatedBy());
        dto.setUpdatedBy(gender.getUpdatedBy());
        dto.setCreatedAt(gender.getCreatedAt());
        dto.setUpdatedAt(gender.getUpdatedAt());
        return dto;
    }
}


