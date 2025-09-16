package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findByMobileNumber(String mobileNumber);
}
