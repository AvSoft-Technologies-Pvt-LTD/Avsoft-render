package com.avsofthealthcare.service.dashboard.doctordashboard;

import java.util.List;

import com.avsofthealthcare.dto.dashboard.doctordashboard.UserPermissionRequestDTO;
import com.avsofthealthcare.dto.dashboard.doctordashboard.UserPermissionResponseDTO;

public interface UserPermissionService {
	List<UserPermissionResponseDTO> getPermissionsByStaffId(Long staffId);
	void assignPermissions(Long staffId, List<UserPermissionRequestDTO> permissionDtos);
}
