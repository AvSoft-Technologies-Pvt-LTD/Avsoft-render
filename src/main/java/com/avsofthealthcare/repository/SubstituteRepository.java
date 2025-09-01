package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.Substitute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubstituteRepository extends JpaRepository<Substitute, Integer> {
	List<Substitute> findByMedicineId(int medicineId);
}