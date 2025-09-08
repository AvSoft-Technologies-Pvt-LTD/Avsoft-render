package com.avsofthealthcare.service.master;

import java.util.List;

import com.avsofthealthcare.dto.master.RolePermissionDto;

public interface RolePermissionService {
	RolePermissionDto assign(RolePermissionDto dto);
	List<RolePermissionDto> getByRole(Long roleId);
	void revoke(Long id);
}