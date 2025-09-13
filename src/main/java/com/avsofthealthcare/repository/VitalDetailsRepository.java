package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.VitalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitalDetailsRepository extends JpaRepository<VitalDetails, Long> {
}
