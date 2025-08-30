package com.avsofthealthcare.service.dashboard.doctordashboard;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffRequestDTO;
import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffResponseDTO;

public interface StaffService {
	StaffResponseDTO create(StaffRequestDTO dto);
	StaffResponseDTO update(Long id, StaffRequestDTO dto);
	void delete(Long id);
	StaffResponseDTO getById(Long id);
	List<StaffResponseDTO> getAll();
}
