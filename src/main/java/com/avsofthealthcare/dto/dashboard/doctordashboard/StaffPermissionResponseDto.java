package com.avsofthealthcare.dto.dashboard.doctordashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffPermissionResponseDto {
	private Long id;
	private Long staffId;
	private Long permissionId;
	private String formName;
	private String action;
	private Boolean active;
}

