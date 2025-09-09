package com.avsofthealthcare.mapper.master;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.avsofthealthcare.dto.master.RolePermissionRequestDto;
import com.avsofthealthcare.dto.master.RolePermissionResponseDto;
import com.avsofthealthcare.dto.master.RolePermissionBulkRequestDto;
import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.entity.master.RolePermission;
import com.avsofthealthcare.entity.master.Permission;
import com.avsofthealthcare.repository.master.PermissionRepository;
import com.avsofthealthcare.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RolePermissionMapper {

	private final RoleRepository roleRepository;
	private final PermissionRepository permissionRepository;

	public RolePermissionResponseDto toResponseDto(RolePermission entity) {
		return RolePermissionResponseDto.builder()
				.id(entity.getId())
				.roleId(entity.getRole().getId())
				.permissionId(entity.getPermission().getId())
				.build();
	}

	public RolePermissionRequestDto toRequestDto(RolePermission entity) {
		return RolePermissionRequestDto.builder()
				.id(entity.getId())
				.roleId(entity.getRole().getId())
				.permissionId(entity.getPermission().getId())
				.build();
	}

	public RolePermission toEntity(RolePermissionRequestDto dto) {
		RolePermission rp = new RolePermission();
		rp.setId(dto.getId());
		rp.setRole(roleRepository.findById(dto.getRoleId())
				.orElseThrow(() -> new RuntimeException("Role not found with ID: " + dto.getRoleId())));
		rp.setPermission(permissionRepository.findById(dto.getPermissionId())
				.orElseThrow(() -> new RuntimeException("Permission not found with ID: " + dto.getPermissionId())));
		return rp;
	}

	public List<RolePermission> toEntityList(RolePermissionBulkRequestDto dto) {
		Role role = roleRepository.findById(dto.getRoleId())
				.orElseThrow(() -> new RuntimeException("Role not found with ID: " + dto.getRoleId()));

		return dto.getPermissionIds().stream()
				.map(permissionId -> {
					Permission permission = permissionRepository.findById(permissionId)
							.orElseThrow(() -> new RuntimeException("Permission not found with ID: " + permissionId));
					RolePermission rp = new RolePermission();
					rp.setRole(role);
					rp.setPermission(permission);
					return rp;
				})
				.collect(Collectors.toList());
	}
}
