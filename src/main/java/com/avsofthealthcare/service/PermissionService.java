package com.avsofthealthcare.service;

import com.avsofthealthcare.entity.Permission;
import java.util.List;

public interface PermissionService {
	Permission savePermission(Permission permission);
	Permission updatePermission(Permission permission);
	void deletePermission(Long permissionId);
	Permission getPermissionById(Long permissionId);
	List<Permission> getAllPermissions();
	List<Permission> getPermissionsByRoleId(Long roleId);
}