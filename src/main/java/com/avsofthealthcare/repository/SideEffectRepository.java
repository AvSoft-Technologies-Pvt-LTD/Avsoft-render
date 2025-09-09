package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.SideEffect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SideEffectRepository extends JpaRepository<SideEffect, Integer> {
	List<SideEffect> findByMedicineId(int medicineId);
}