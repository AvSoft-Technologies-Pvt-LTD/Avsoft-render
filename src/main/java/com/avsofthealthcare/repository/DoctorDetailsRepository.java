package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.DoctorDetails;
import com.avsofthealthcare.entity.PatientDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Long> {
	List<DoctorDetails> findAllByIsDeletedFalse();

	Optional<DoctorDetails> findByIdAndIsDeletedFalse(Long id);
}