package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface PatientDetailsRepository extends JpaRepository<PatientDetails, Long> {

    List<PatientDetails> findAllByIsDeletedFalse();

    Optional<PatientDetails> findByIdAndIsDeletedFalse(Long id);
}