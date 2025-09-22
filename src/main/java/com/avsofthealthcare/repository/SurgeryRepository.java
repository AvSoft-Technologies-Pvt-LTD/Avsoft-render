package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurgeryRepository extends JpaRepository<Surgery, Long> {
    List<Surgery> findByUserId(Long userId);
}
