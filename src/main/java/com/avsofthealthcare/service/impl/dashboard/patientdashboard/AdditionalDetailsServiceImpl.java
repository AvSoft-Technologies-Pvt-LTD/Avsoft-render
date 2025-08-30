package com.avsofthealthcare.service.impl.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.AdditionalDetailsRequestDTO;
import com.avsofthealthcare.dto.dashboard.patientdashboard.AdditionalDetailsResponseDTO;
import com.avsofthealthcare.entity.dashboard.patientdashboard.AdditionalDetails;
import com.avsofthealthcare.entity.master.CoverageType;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.dashboard.patientdashboard.AdditionalDetailsMapper;
import com.avsofthealthcare.repository.dashboard.patientdashboard.AdditionalDetailsRepository;
import com.avsofthealthcare.repository.master.CoverageTypeRepository;
import com.avsofthealthcare.service.dashboard.patientdashboard.AdditionalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdditionalDetailsServiceImpl implements AdditionalDetailsService {

	@Autowired
	private AdditionalDetailsRepository additionalDetailsRepository;

	@Autowired
	private CoverageTypeRepository coverageTypeRepository;

	@Override
	public AdditionalDetailsResponseDTO create(String patientId, AdditionalDetailsRequestDTO dto) {
		// Optional: Check for existing record
		if (additionalDetailsRepository.existsByPatientId(patientId)) {
			throw new IllegalStateException("Additional details already exist for patientId: " + patientId);
		}

		CoverageType coverageType = coverageTypeRepository.findById(dto.getCoverageTypeId())
				.orElseThrow(() -> new ResourceNotFoundException("Coverage Type not found with id: " + dto.getCoverageTypeId()));

		AdditionalDetails entity = AdditionalDetailsMapper.toEntity(dto, patientId, coverageType);
		return AdditionalDetailsMapper.toDto(additionalDetailsRepository.save(entity));
	}

	@Override
	public AdditionalDetailsResponseDTO findByPatientId(String patientId) {
		AdditionalDetails entity = additionalDetailsRepository.findByPatientId(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Additional details not found for patientId: " + patientId));
		return AdditionalDetailsMapper.toDto(entity);
	}

	@Override
	public AdditionalDetailsResponseDTO updateByPatientId(String patientId, AdditionalDetailsRequestDTO dto) {
		AdditionalDetails existing = additionalDetailsRepository.findByPatientId(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Additional details not found for patientId: " + patientId));

		CoverageType coverageType = coverageTypeRepository.findById(dto.getCoverageTypeId())
				.orElseThrow(() -> new ResourceNotFoundException("Coverage Type not found with id: " + dto.getCoverageTypeId()));

		// Update fields
		existing.setInsuranceProviderName(dto.getInsuranceProviderName());
		existing.setPolicyNum(dto.getPolicyNum());
		existing.setCoverageType(coverageType);
		existing.setCoverageAmount(dto.getCoverageAmount());
		existing.setIsPrimaryHolder(dto.isPrimaryHolder());
		existing.setPolicyStartDate(dto.getPolicyStartDate());
		existing.setPolicyEndDate(dto.getPolicyEndDate());

		// Audit (optional)
		existing.setUpdatedBy("admin");
		existing.setUpdatedAt(LocalDateTime.now());

		return AdditionalDetailsMapper.toDto(additionalDetailsRepository.save(existing));
	}

	@Override
	public void delete(String patientId) {
		AdditionalDetails entity = additionalDetailsRepository.findByPatientId(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Additional details not found for patientId: " + patientId));
		additionalDetailsRepository.delete(entity);
	}
}