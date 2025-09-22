package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.IpdMedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IpdMedicalRecordRepository extends JpaRepository<IpdMedicalRecord, Integer> {
    List<IpdMedicalRecord> findByUserId(Long userId);
}
