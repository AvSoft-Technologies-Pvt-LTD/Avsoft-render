package com.avsofthealthcare.service.impl.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.PatientVitalsRequestDTO;
import com.avsofthealthcare.dto.dashboard.patientdashboard.PatientVitalsResponseDTO;
import com.avsofthealthcare.entity.User;  // your existing User entity
import com.avsofthealthcare.entity.dashboard.patientdashboard.PatientVitals;
import com.avsofthealthcare.mapper.dashboard.patientdashboard.PatientVitalsMapper;
import com.avsofthealthcare.repository.dashboard.patientdashboard.PatientVitalsRepository;
import com.avsofthealthcare.service.dashboard.patientdashboard.PatientVitalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientVitalsServiceImpl implements PatientVitalsService {

	private final PatientVitalsRepository patientVitalsRepository;

	@Override
	public PatientVitalsResponseDTO create(PatientVitalsRequestDTO dto) {
		PatientVitals entity = PatientVitalsMapper.toEntity(dto);
		patientVitalsRepository.save(entity);
		return PatientVitalsMapper.toResponseDTO(entity);
	}

	@Override
	public PatientVitalsResponseDTO update(Long id, PatientVitalsRequestDTO dto) {
		PatientVitals entity = patientVitalsRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Patient Vitals not found with id " + id));

		// Update fields from request
		PatientVitalsMapper.updateEntity(entity, dto);

		// Set updatedBy & updatedAt explicitly
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated() &&
				authentication.getPrincipal() instanceof User principal) {
			entity.setUpdatedBy(String.valueOf(principal.getId()));
		}
		entity.setUpdatedAt(LocalDateTime.now());

		patientVitalsRepository.save(entity);

		return PatientVitalsMapper.toResponseDTO(entity);
	}


	@Override
	public PatientVitalsResponseDTO getById(Long id) {
		PatientVitals entity = patientVitalsRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Patient Vitals not found with id " + id));
		return PatientVitalsMapper.toResponseDTO(entity);
	}

	@Override
	public List<PatientVitalsResponseDTO> getAll() {
		return patientVitalsRepository.findAll()
				.stream()
				.map(PatientVitalsMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Long id) {
		if (!patientVitalsRepository.existsById(id)) {
			throw new RuntimeException("Patient Vitals not found with id " + id);
		}
		patientVitalsRepository.deleteById(id);
	}
}
