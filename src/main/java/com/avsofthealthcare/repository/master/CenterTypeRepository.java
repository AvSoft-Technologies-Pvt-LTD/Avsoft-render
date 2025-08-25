package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.CenterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CenterTypeRepository extends JpaRepository<CenterType, Integer> {
    List<CenterType> findAllByIsDeletedFalse();
    Optional<CenterType> findByIdAndIsDeletedFalse(Integer id);
}
