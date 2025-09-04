package com.avsofthealthcare.dto.dashboard.doctordashboard;

import lombok.Data;

// DTO for returning permission data
@Data
public class UserPermissionResponseDTO {
	private String formName;
	private Boolean canAdd;
	private Boolean canEdit;
	private Boolean canView;

	// Constructors, getters, setters
}
