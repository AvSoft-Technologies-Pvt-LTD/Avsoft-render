package com.avsofthealthcare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.avsofthealthcare.entity.Permission;
import com.avsofthealthcare.repository.PermissionRepository;
import com.avsofthealthcare.service.PermissionService;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public Permission savePermission(Permission permission) {
		return permissionRepository.save(permission);
	}

	@Override
	public Permission updatePermission(Permission permission) {
		Optional<Permission> existingPermOpt = permissionRepository.findById(permission.getId());
		if (existingPermOpt.isPresent()) {
			Permission existingPerm = existingPermOpt.get();
			existingPerm.setRole(permission.getRole());
			existingPerm.setFormName(permission.getFormName());
			existingPerm.setCanAdd(permission.getCanAdd());
			existingPerm.setCanEdit(permission.getCanEdit());
			existingPerm.setCanView(permission.getCanView());
			return permissionRepository.save(existingPerm);
		} else {
			throw new RuntimeException("Permission not found with id " + permission.getId());
		}
	}

	@Override
	public void deletePermission(Long permissionId) {
		permissionRepository.deleteById(permissionId);
	}

	@Override
	public Permission getPermissionById(Long permissionId) {
		return permissionRepository.findById(permissionId)
				.orElseThrow(() -> new RuntimeException("Permission not found with id " + permissionId));
	}

	@Override
	public List<Permission> getAllPermissions() {
		return permissionRepository.findAll();
	}

	@Override
	public List<Permission> getPermissionsByRoleId(Long roleId) {
		return permissionRepository.findByRoleId(roleId);
	}
}