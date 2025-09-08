package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.PermissionDto;

import java.util.List;

public interface PermissionService {
	PermissionDto create(PermissionDto dto);
	List<PermissionDto> getAll();
	PermissionDto update(Long id, PermissionDto dto);
	void delete(Long id);
	List<PermissionDto> getPermissionsByRoleId(Long roleId);
	List<PermissionDto> getPermissionsByRoleName(String roleName);
}