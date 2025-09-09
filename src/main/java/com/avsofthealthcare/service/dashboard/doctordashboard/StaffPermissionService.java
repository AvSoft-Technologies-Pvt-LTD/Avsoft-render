package com.avsofthealthcare.service.dashboard.doctordashboard;

import java.util.List;

import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffPermissionBulkRequestDto;
import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffPermissionRequestDto;
import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffPermissionResponseDto;

public interface StaffPermissionService {

	StaffPermissionResponseDto assignPermission(Long staffId, StaffPermissionRequestDto requestDto);

	List<StaffPermissionResponseDto> bulkAssignPermissions(Long staffId, StaffPermissionBulkRequestDto bulkRequest);

	List<StaffPermissionResponseDto> getStaffPermissions(Long staffId);

	void removePermission(Long staffPermissionId);
}

