package com.avsofthealthcare.mapper.master;

import org.springframework.stereotype.Component;

import com.avsofthealthcare.dto.master.UserPermissionDto;
import com.avsofthealthcare.entity.master.UserPermission;
import com.avsofthealthcare.repository.master.PermissionRepository;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffDetailsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPermissionMapper {
	private final StaffDetailsRepository staffDetailsRepository;
	private final PermissionRepository permissionRepository;

	public UserPermissionDto toDto(UserPermission entity) {
		return UserPermissionDto.builder()
				.id(entity.getId())
				.staffId(entity.getStaff().getId())
				.permissionId(entity.getPermission().getId())
				.allowed(entity.getAllowed())
				.build();
	}

	public UserPermission toEntity(UserPermissionDto dto) {
		UserPermission up = new UserPermission();
		up.setId(dto.getId());
		up.setStaff(staffDetailsRepository.findById(dto.getStaffId())
				.orElseThrow(() -> new RuntimeException("Staff not found")));
		up.setPermission(permissionRepository.findById(dto.getPermissionId())
				.orElseThrow(() -> new RuntimeException("Permission not found")));
		up.setAllowed(dto.getAllowed());
		return up;
	}
}