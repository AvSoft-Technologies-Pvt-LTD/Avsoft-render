package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.LabDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabDetailsRepository extends JpaRepository<LabDetails, Long> {
}
