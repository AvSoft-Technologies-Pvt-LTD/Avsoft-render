package com.avsofthealthcare.repository.dashboard.doctordashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;

public interface StaffRepository extends JpaRepository<StaffDetails, Long> {
    Optional<StaffDetails> findByEmailId(String emailId);
    Optional<StaffDetails> findByPhoneNumber(String phoneNumber);
}
