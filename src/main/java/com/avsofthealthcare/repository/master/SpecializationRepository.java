package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer> {
    List<Specialization> findAllByIsDeletedFalse();

    Optional<Specialization> findByIdAndIsDeletedFalse(Integer id);
	List<Specialization> findByPracticeTypeIdAndActiveTrueAndIsDeletedFalse(Integer practiceTypeId);
}


