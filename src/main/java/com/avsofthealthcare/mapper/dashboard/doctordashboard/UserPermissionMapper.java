package com.avsofthealthcare.mapper.dashboard.doctordashboard;

import com.avsofthealthcare.dto.dashboard.doctordashboard.UserPermissionRequestDTO;
import com.avsofthealthcare.dto.dashboard.doctordashboard.UserPermissionResponseDTO;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;
import com.avsofthealthcare.entity.dashboard.doctordashboard.UserPermission;

public class UserPermissionMapper  {

	// Map from DTO request to entity
	public static UserPermission toEntity(UserPermissionRequestDTO dto, StaffDetails staff) {
		if (dto == null) {
			return null;
		}
		UserPermission permission = new UserPermission();

		// Set the staff manually
		permission.setStaff(staff);

		// Map fields from DTO to entity
		permission.setFormName(dto.getFormName());
		permission.setCanAdd(dto.getCanAdd() != null ? dto.getCanAdd() : false);
		permission.setCanEdit(dto.getCanEdit() != null ? dto.getCanEdit() : false);
		permission.setCanView(dto.getCanView() != null ? dto.getCanView() : false);

		return permission;
	}

	// Map from entity to response DTO
	public static UserPermissionResponseDTO toResponseDTO(UserPermission entity) {
		if (entity == null) {
			return null;
		}
		UserPermissionResponseDTO dto = new UserPermissionResponseDTO();

		// Map fields from entity to DTO
		dto.setFormName(entity.getFormName());
		dto.setCanAdd(entity.getCanAdd());
		dto.setCanEdit(entity.getCanEdit());
		dto.setCanView(entity.getCanView());

		return dto;
	}
}