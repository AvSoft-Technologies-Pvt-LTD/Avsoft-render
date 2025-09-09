package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.MedicalConditions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicalConditionsRepository extends JpaRepository<MedicalConditions, Integer> {
    List<MedicalConditions> findAllByIsDeletedFalse();
    Optional<MedicalConditions> findByIdAndIsDeletedFalse(Integer id);
}

