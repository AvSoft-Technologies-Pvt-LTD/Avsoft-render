package com.avsofthealthcare.mapper.master;

import org.springframework.stereotype.Component;

import com.avsofthealthcare.dto.master.RolePermissionDto;
import com.avsofthealthcare.entity.master.RolePermission;
import com.avsofthealthcare.repository.master.PermissionRepository;
import com.avsofthealthcare.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RolePermissionMapper {
	private final RoleRepository roleRepository;
	private final PermissionRepository permissionRepository;

	public RolePermissionDto toDto(RolePermission entity) {
		return RolePermissionDto.builder()
				.id(entity.getId())
				.roleId(entity.getRole().getId())
				.permissionId(entity.getPermission().getId())
				.build();
	}

	public RolePermission toEntity(RolePermissionDto dto) {
		RolePermission rp = new RolePermission();
		rp.setId(dto.getId());
		rp.setRole(roleRepository.findById(dto.getRoleId())
				.orElseThrow(() -> new RuntimeException("Role not found")));
		rp.setPermission(permissionRepository.findById(dto.getPermissionId())
				.orElseThrow(() -> new RuntimeException("Permission not found")));
		return rp;
	}
}

