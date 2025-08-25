package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.HospitalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalTypeRepository extends JpaRepository<HospitalType, Integer> {
    List<HospitalType> findAllByIsDeletedFalse();
    Optional<HospitalType> findByIdAndIsDeletedFalse(Integer id);
}
