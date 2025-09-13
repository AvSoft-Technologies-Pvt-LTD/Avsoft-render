package com.avsofthealthcare.repository.dashboard.doctordashboard;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;

public interface StaffDetailsRepository extends JpaRepository<StaffDetails, Long> {
    Optional<StaffDetails> findByEmailId(String emailId);
    Optional<StaffDetails> findByPhoneNumber(String phoneNumber);
	List<StaffDetails> findByUser_Role_Id(Long roleId);

	List<StaffDetails> findByUser_Role_Name(String roleName);
	Optional<StaffDetails> findByUser(User user);

}
