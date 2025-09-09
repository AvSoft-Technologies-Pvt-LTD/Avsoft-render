package com.avsofthealthcare.mapper.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.AdditionalDetailsRequestDTO;
import com.avsofthealthcare.dto.dashboard.patientdashboard.AdditionalDetailsResponseDTO;
import com.avsofthealthcare.entity.dashboard.patientdashboard.AdditionalDetails;
import com.avsofthealthcare.entity.master.CoverageType;

public class AdditionalDetailsMapper {

	public static AdditionalDetails toEntity(AdditionalDetailsRequestDTO dto, String patientId, CoverageType coverageType) {
		return AdditionalDetails.builder()
				.patientId(patientId)
				.insuranceProviderName(dto.getInsuranceProviderName())
				.policyNum(dto.getPolicyNum())
				.coverageType(coverageType)
				.coverageAmount(dto.getCoverageAmount())
				.isPrimaryHolder(dto.isPrimaryHolder())
				.policyStartDate(dto.getPolicyStartDate())
				.policyEndDate(dto.getPolicyEndDate())
				.build();
	}

	public static AdditionalDetailsResponseDTO toDto(AdditionalDetails entity) {
		AdditionalDetailsResponseDTO dto = new AdditionalDetailsResponseDTO();

		dto.setId(entity.getId());
		dto.setPatientId(entity.getPatientId());
		dto.setInsuranceProviderName(entity.getInsuranceProviderName());
		dto.setPolicyNum(entity.getPolicyNum());

		if (entity.getCoverageType() != null) {
			dto.setCoverageTypeId(entity.getCoverageType().getId());
			dto.setCoverageTypeName(entity.getCoverageType().getCoverageTypeName());
		}

		dto.setCoverageAmount(entity.getCoverageAmount());
		dto.setPrimaryHolder(entity.getIsPrimaryHolder());
		dto.setPolicyStartDate(entity.getPolicyStartDate());
		dto.setPolicyEndDate(entity.getPolicyEndDate());

		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedBy(entity.getUpdatedBy());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}
}
