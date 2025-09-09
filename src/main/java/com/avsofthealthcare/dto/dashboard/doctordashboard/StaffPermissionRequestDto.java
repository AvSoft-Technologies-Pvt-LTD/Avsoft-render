package com.avsofthealthcare.dto.dashboard.doctordashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffPermissionRequestDto {
	private Long staffId;
	private Long permissionId;
}
