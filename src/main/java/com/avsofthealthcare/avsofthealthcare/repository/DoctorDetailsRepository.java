package com.avsofthealthcare.avsofthealthcare.repository;

import com.avsofthealthcare.avsofthealthcare.entity.DoctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, Long> {
    Optional<DoctorDetails> findByRegistrationNumber(String registrationNumber);
}
