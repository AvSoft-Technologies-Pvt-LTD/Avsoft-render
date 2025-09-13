package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
    List<Gender> findAllByIsDeletedFalse();
    Optional<Gender> findByIdAndIsDeletedFalse(Integer id);
}

