package com.avsofthealthcare.service.dashboard.doctordashboard;

import java.util.List;

import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffRequestDTO;
import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffResponseDTO;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;

public interface StaffService {
	StaffResponseDTO create(StaffRequestDTO dto);
	StaffResponseDTO update(Long id, StaffRequestDTO dto);
	void delete(Long id);
	StaffResponseDTO getById(Long id);
	List<StaffResponseDTO> getAll();
	List<StaffDetails> getStaffByRoleId(Long roleId);
	List<StaffDetails> getStaffByRoleName(String roleName);
}
