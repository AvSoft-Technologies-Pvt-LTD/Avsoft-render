package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.HealthConditions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HealthConditionsRepository extends JpaRepository<HealthConditions, Integer> {
    List<HealthConditions> findAllByIsDeletedFalse();
    Optional<HealthConditions> findByIdAndIsDeletedFalse(Integer id);
}

