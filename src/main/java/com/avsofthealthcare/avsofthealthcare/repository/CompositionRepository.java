package com.avsofthealthcare.avsofthealthcare.repository;

import com.avsofthealthcare.avsofthealthcare.entity.Composition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import java.util.List;

public interface CompositionRepository extends JpaRepository<Composition, Integer> {
    List<Composition> findByMedicineId(int medicineId);
    Page<Composition> findByCompositionContainingIgnoreCase(String composition, Pageable pageable);


}
