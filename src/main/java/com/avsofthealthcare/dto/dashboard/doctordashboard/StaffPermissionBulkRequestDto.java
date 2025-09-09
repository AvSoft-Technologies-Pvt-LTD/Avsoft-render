package com.avsofthealthcare.dto.dashboard.doctordashboard;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffPermissionBulkRequestDto {
	private Long staffId;
	private List<Long> permissionIds;
}

