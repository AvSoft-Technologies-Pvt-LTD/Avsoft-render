package com.avsofthealthcare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.avsofthealthcare.entity.Permission;
import com.avsofthealthcare.entity.Role;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
	List<Permission> findByRoleId(Long roleId);
	List<Permission> findByRole(Optional<Role> role);
	// Add custom queries if needed
}