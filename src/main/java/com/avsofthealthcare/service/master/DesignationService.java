package com.avsofthealthcare.service.master;

import java.util.List;

import com.avsofthealthcare.dto.master.DesignationRequestDTO;
import com.avsofthealthcare.dto.master.DesignationResponseDTO;

public interface DesignationService {
	DesignationResponseDTO create(DesignationRequestDTO dto);
	DesignationResponseDTO update(Long id, DesignationRequestDTO dto);
	void delete(Long id);
	DesignationResponseDTO getById(Long id);
	List<DesignationResponseDTO> getAll();
}
