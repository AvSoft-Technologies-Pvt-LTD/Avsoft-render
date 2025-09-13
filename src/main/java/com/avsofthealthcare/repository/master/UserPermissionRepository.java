package com.avsofthealthcare.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import com.avsofthealthcare.entity.master.UserPermission;
import java.util.List;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
	List<UserPermission> findByStaffId(Long staffId);
	void deleteAllByStaffId(Long staffId);
}