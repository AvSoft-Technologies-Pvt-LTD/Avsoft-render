package com.avsofthealthcare.mapper.master;

import com.avsofthealthcare.dto.master.DesignationRequestDTO;
import com.avsofthealthcare.dto.master.DesignationResponseDTO;
import com.avsofthealthcare.entity.master.Designation;

public class DesignationMapper {

	public static Designation toEntity(DesignationRequestDTO dto) {
		return Designation.builder()
				.name(dto.getName())
				.build();
	}

	public static DesignationResponseDTO toResponseDTO(Designation entity) {
		return DesignationResponseDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.createdAt(entity.getCreatedAt())
				.updatedAt(entity.getUpdatedAt())
				.createdBy(entity.getCreatedBy())
				.updatedBy(entity.getUpdatedBy())
				.build();
	}
}
