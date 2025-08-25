package com.avsofthealthcare.service.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.AdditionalDetailsRequestDTO;
import com.avsofthealthcare.dto.dashboard.patientdashboard.AdditionalDetailsResponseDTO;

public interface AdditionalDetailsService {
	AdditionalDetailsResponseDTO create(String patientId, AdditionalDetailsRequestDTO dto);
	AdditionalDetailsResponseDTO findByPatientId(String patientId);
	AdditionalDetailsResponseDTO updateByPatientId(String patientId, AdditionalDetailsRequestDTO dto);
	void delete(String patientId);
}
