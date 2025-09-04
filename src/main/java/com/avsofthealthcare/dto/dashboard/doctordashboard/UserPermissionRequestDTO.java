package com.avsofthealthcare.dto.dashboard.doctordashboard;

import lombok.Data;

// DTO for individual permission assignment
@Data
public class UserPermissionRequestDTO {
	private String formName;
	private Boolean canAdd;
	private Boolean canEdit;
	private Boolean canView;

	// Constructors, getters, setters
}
