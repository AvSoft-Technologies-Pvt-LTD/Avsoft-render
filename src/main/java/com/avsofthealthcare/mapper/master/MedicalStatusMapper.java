package com.avsofthealthcare.mapper.master;


import com.avsofthealthcare.dto.master.MedicalStatusRequestDto;
import com.avsofthealthcare.dto.master.MedicalStatusResponseDto;
import com.avsofthealthcare.entity.master.MedicalStatus;

public class MedicalStatusMapper {

    public static MedicalStatus toEntity(MedicalStatusRequestDto dto) {
        return MedicalStatus.builder()
                .statusName(dto.getStatusName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static MedicalStatusResponseDto toDto(MedicalStatus gender) {
        MedicalStatusResponseDto dto = new MedicalStatusResponseDto();
        dto.setId(gender.getId());
        dto.setStatusName(gender.getStatusName());
        dto.setDescription(gender.getDescription());
        dto.setActive(gender.getActive());

        dto.setCreatedBy(gender.getCreatedBy());
        dto.setUpdatedBy(gender.getUpdatedBy());
        dto.setCreatedAt(gender.getCreatedAt());
        dto.setUpdatedAt(gender.getUpdatedAt());
        return dto;
    }
}
