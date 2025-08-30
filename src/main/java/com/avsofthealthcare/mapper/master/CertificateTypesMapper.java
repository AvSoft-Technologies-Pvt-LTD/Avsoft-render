package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.CertificateTypesRequestDto;
import com.avsofthealthcare.dto.master.CertificateTypesResponseDto;
import com.avsofthealthcare.entity.master.CertificateTypes;

public class CertificateTypesMapper {

    public static CertificateTypes toEntity(CertificateTypesRequestDto dto) {
        return CertificateTypes.builder()
                .certificateName(dto.getCertificateName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static CertificateTypesResponseDto toDto(CertificateTypes certificateTypes) {
        CertificateTypesResponseDto dto = new CertificateTypesResponseDto();
        dto.setId(certificateTypes.getId());
        dto.setCertificateName(certificateTypes.getCertificateName());
        dto.setDescription(certificateTypes.getDescription());
        dto.setActive(certificateTypes.getActive());

        dto.setCreatedBy(certificateTypes.getCreatedBy());
        dto.setUpdatedBy(certificateTypes.getUpdatedBy());
        dto.setCreatedAt(certificateTypes.getCreatedAt());
        dto.setUpdatedAt(certificateTypes.getUpdatedAt());
        return dto;
    }
}
