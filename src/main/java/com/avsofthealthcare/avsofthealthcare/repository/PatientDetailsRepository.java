package com.avsofthealthcare.avsofthealthcare.repository;

import com.avsofthealthcare.avsofthealthcare.entity.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDetailsRepository extends JpaRepository<PatientDetails, Long> {
}
