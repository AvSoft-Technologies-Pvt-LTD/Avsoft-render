package com.avsofthealthcare.repository.dashboard.doctordashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import com.avsofthealthcare.entity.dashboard.doctordashboard.UserPermission;
import java.util.List;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
	List<UserPermission> findByStaffId(Long staffId);
	void deleteAllByStaffId(Long staffId);
}