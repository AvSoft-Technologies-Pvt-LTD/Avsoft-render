package com.avsofthealthcare.service.master;

import java.util.List;

import com.avsofthealthcare.dto.master.RolePermissionBulkRequestDto;
import com.avsofthealthcare.dto.master.RolePermissionRequestDto;
import com.avsofthealthcare.dto.master.RolePermissionResponseDto;

public interface RolePermissionService {
	RolePermissionResponseDto assign(RolePermissionRequestDto dto);
	List<RolePermissionResponseDto> assignBulk(RolePermissionBulkRequestDto dto);
	List<RolePermissionResponseDto> getByRole(Long roleId);
	List<RolePermissionResponseDto> getAll();
	void revoke(Long id);
}
