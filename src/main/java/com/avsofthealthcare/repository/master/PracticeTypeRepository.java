package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.PracticeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PracticeTypeRepository extends JpaRepository<PracticeType, Integer> {
    List<PracticeType> findAllByIsDeletedFalse();

    Optional<PracticeType> findByIdAndIsDeletedFalse(Integer id);
}


