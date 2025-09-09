package com.avsofthealthcare.repository.master;


import com.avsofthealthcare.entity.master.MedicalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicalStatusRepository extends JpaRepository<MedicalStatus, Integer> {
    List<MedicalStatus> findAllByIsDeletedFalse();
    Optional<MedicalStatus> findByIdAndIsDeletedFalse(Integer id);
}
