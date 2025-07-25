package com.avsofthealthcare.avsofthealthcare.repository;

import com.avsofthealthcare.avsofthealthcare.entity.HospitalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalDetailsRepository extends JpaRepository<HospitalDetails, Long> {
}
