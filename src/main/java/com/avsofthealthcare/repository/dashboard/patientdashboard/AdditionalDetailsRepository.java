package com.avsofthealthcare.repository.dashboard.patientdashboard;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avsofthealthcare.entity.dashboard.patientdashboard.AdditionalDetails;

public interface AdditionalDetailsRepository extends JpaRepository<AdditionalDetails, Long> {
	Optional<AdditionalDetails> findByPatientId(String patientId);
	boolean existsByPatientId(String patientId);
}
