package com.avsofthealthcare.mapper.dashboard.doctordashboard;

import org.springframework.stereotype.Component;

import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffPermissionBulkRequestDto;
import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffPermissionRequestDto;
import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffPermissionResponseDto;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffPermission;
import com.avsofthealthcare.entity.master.Permission;

@Component
public class StaffPermissionMapper {

	// ✅ For single assign (RequestDto → Entity)
	public StaffPermission toEntity(StaffPermissionRequestDto dto, StaffDetails staff, Permission permission) {
		return StaffPermission.builder()
				.staff(staff)
				.permission(permission)
				.active(true)
				.build();
	}

	// ✅ For bulk assign (Permission → Entity)
	public StaffPermission toEntity(StaffDetails staff, Permission permission) {
		return StaffPermission.builder()
				.staff(staff)
				.permission(permission)
				.active(true)
				.build();
	}

	// ✅ Entity → ResponseDto
	public StaffPermissionResponseDto toResponseDto(StaffPermission entity) {
		return StaffPermissionResponseDto.builder()
				.id(entity.getId())
				.staffId(entity.getStaff().getId())
				.permissionId(entity.getPermission().getId())
				.formName(entity.getPermission().getFormName())
				.action(entity.getPermission().getAction())
				.active(entity.getActive())
				.build();
	}
}

