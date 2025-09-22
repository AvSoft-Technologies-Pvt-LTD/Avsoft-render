package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.VirtualMedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirtualMedicalRecordRepository extends JpaRepository<VirtualMedicalRecord, Integer> {
    List<VirtualMedicalRecord> findByUserId(Long userId);
}
