package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.Use;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UseRepository extends JpaRepository<Use, Integer> {
	List<Use> findByMedicineId(int medicineId);


}