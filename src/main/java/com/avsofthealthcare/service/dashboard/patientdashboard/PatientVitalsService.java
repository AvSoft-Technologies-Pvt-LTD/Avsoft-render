package com.avsofthealthcare.service.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.PatientVitalsRequestDTO;
import com.avsofthealthcare.dto.dashboard.patientdashboard.PatientVitalsResponseDTO;

import java.util.List;

public interface PatientVitalsService {

	PatientVitalsResponseDTO create(PatientVitalsRequestDTO dto);

	PatientVitalsResponseDTO update(Long id, PatientVitalsRequestDTO dto);

	PatientVitalsResponseDTO getById(Long id);

	List<PatientVitalsResponseDTO> getAll();

	void delete(Long id);
}
