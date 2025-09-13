package com.avsofthealthcare.service.master;

import java.util.List;

import com.avsofthealthcare.dto.master.UserPermissionDto;

public interface UserPermissionService {
	UserPermissionDto assign(UserPermissionDto dto);
	List<UserPermissionDto> getByStaff(Long staffId);
	void revoke(Long id);
}
