package com.avsofthealthcare.repository.master;


import com.avsofthealthcare.entity.master.AvailableTests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvailableTestsRepository extends JpaRepository<AvailableTests, Integer> {
    List<AvailableTests> findAllByIsDeletedFalse();
    Optional<AvailableTests> findByIdAndIsDeletedFalse(Integer id);

}

