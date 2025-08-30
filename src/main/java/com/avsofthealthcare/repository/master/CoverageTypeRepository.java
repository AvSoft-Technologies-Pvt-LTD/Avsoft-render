package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.CoverageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoverageTypeRepository extends JpaRepository<CoverageType, Integer> {
    List<CoverageType> findAllByIsDeletedFalse();
    Optional<CoverageType> findByIdAndIsDeletedFalse(Integer id);
}
