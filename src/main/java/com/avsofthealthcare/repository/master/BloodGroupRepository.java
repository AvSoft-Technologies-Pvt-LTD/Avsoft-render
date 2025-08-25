package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodGroupRepository extends JpaRepository<BloodGroup, Integer> {
    List<BloodGroup> findAllByIsDeletedFalse();
    Optional<BloodGroup> findByIdAndIsDeletedFalse(Integer id);
}
