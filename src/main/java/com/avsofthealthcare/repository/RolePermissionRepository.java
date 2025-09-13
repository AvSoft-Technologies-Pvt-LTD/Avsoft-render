package com.avsofthealthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avsofthealthcare.entity.master.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
	List<RolePermission> findByRoleId(Long roleId);
}
