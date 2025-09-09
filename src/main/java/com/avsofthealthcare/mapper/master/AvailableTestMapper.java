package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.AvailableTestRequestDto;
import com.avsofthealthcare.dto.master.AvailableTestResponseDto;
import com.avsofthealthcare.entity.master.AvailableTests;

public class AvailableTestMapper {

    public static AvailableTests toEntity(AvailableTestRequestDto dto) {
        return AvailableTests.builder()
                .testName(dto.getTestName())
                .description(dto.getDescription())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .isDeleted(false)
                .build();
    }

    public static AvailableTestResponseDto toDto(AvailableTests test) {
        AvailableTestResponseDto dto = new AvailableTestResponseDto();
        dto.setId(test.getId());
        dto.setTestName(test.getTestName());
        dto.setDescription(test.getDescription());
        dto.setActive(test.getActive());

        dto.setCreatedBy(test.getCreatedBy());
        dto.setUpdatedBy(test.getUpdatedBy());
        dto.setCreatedAt(test.getCreatedAt());
        dto.setUpdatedAt(test.getUpdatedAt());
        return dto;
    }
}
