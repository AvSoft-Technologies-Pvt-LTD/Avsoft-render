package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.OpdMedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OpdMedicalRecordRepository extends JpaRepository<OpdMedicalRecord, Long> {
    List<OpdMedicalRecord> findByUserId(Long userId);
}
