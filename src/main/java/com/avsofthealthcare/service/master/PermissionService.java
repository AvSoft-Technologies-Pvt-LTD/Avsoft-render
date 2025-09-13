package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.PermissionRequestDto;
import com.avsofthealthcare.dto.master.PermissionResponseDto;

import java.util.List;

public interface PermissionService {
	PermissionResponseDto create(PermissionRequestDto dto);
	List<PermissionResponseDto> getAll();
	PermissionResponseDto update(Long id, PermissionRequestDto dto);
	void delete(Long id);
	List<PermissionResponseDto> getPermissionsByRoleId(Long roleId);
	List<PermissionResponseDto> getPermissionsByRoleName(String roleName);
}
