package com.avsofthealthcare.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.avsofthealthcare.entity.master.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	// Correct way to search by role.id
	List<Permission> findByRole_Id(Long roleId);

	// Or search by role.name if you prefer
	List<Permission> findByRole_Name(String roleName);
}
