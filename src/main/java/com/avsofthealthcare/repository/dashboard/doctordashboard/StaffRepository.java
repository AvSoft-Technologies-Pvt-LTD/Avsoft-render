package com.avsofthealthcare.repository.dashboard.doctordashboard;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;

public interface StaffRepository extends JpaRepository<StaffDetails, Long> {
}
