package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByUserId(Long userId);
    List<MedicalRecord> findByType(String type);
    List<MedicalRecord> findByUserIdAndType(Long userId, String type); // Add this
}
