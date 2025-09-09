package com.avsofthealthcare.repository.dashboard.doctordashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffPermission;
import com.avsofthealthcare.entity.master.Permission;

@Repository
public interface StaffPermissionRepository extends JpaRepository<StaffPermission, Long> {


	boolean existsByStaffAndPermission(StaffDetails staff, Permission permission);

	List<StaffPermission> findByStaff(StaffDetails staff);

	List<StaffPermission> findByStaffIdAndActiveTrue(Long staffId);
	List<StaffPermission> findByStaff_Id(Long staffId);



}
