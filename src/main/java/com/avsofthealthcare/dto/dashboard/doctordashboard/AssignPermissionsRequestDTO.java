package com.avsofthealthcare.dto.dashboard.doctordashboard;

import java.util.List;

import lombok.Data;

// DTO for assigning multiple permissions to a staff member
@Data
public class AssignPermissionsRequestDTO {
	private Long staffId;
	private List<UserPermissionRequestDTO> permissions;

	// Constructors, getters, setters
}
