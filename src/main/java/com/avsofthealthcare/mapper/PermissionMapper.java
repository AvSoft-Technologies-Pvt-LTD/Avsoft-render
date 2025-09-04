package com.avsofthealthcare.mapper;

import com.avsofthealthcare.entity.Permission;
import com.avsofthealthcare.dto.PermissionRequestDTO;
import com.avsofthealthcare.dto.PermissionResponseDTO;
import com.avsofthealthcare.entity.Role;

public class PermissionMapper {

	public static Permission toEntity(PermissionRequestDTO dto) {
		if (dto == null) {
			return null;
		}
		Permission permission = new Permission();
		// Map roleId to Role entity
		Role role = new Role();
		role.setId(dto.getRoleId());
		permission.setRole(role);
		permission.setFormName(dto.getFormName());
		permission.setCanAdd(dto.getCanAdd());
		permission.setCanEdit(dto.getCanEdit());
		permission.setCanView(dto.getCanView());
		return permission;
	}

	public static PermissionResponseDTO toResponseDTO(Permission permission) {
		if (permission == null) {
			return null;
		}
		PermissionResponseDTO dto = new PermissionResponseDTO();
		dto.setId(permission.getId());
		if (permission.getRole() != null) {
			dto.setRoleId(permission.getRole().getId());
			// Optionally, set roleName if available
			// dto.setRoleName(permission.getRole().getName());
		}
		dto.setFormName(permission.getFormName());
		dto.setCanAdd(permission.getCanAdd());
		dto.setCanEdit(permission.getCanEdit());
		dto.setCanView(permission.getCanView());
		return dto;
	}
}