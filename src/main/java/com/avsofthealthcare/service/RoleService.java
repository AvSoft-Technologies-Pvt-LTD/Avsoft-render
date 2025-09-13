package com.avsofthealthcare.service;

import java.util.List;

import com.avsofthealthcare.dto.RoleRequestDto;
import com.avsofthealthcare.dto.RoleResponseDto;
import com.avsofthealthcare.dto.RoleUpdateDto;
import com.avsofthealthcare.entity.Role;

public interface RoleService {
	RoleResponseDto createRole(RoleRequestDto roleRequestDto);
	RoleResponseDto updateRole(Long id, RoleUpdateDto roleUpdateDto);
	void deleteRole(Long id);
	RoleResponseDto getRoleById(Long id);
	List<RoleResponseDto> getAllRoles();
}