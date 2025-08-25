package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.SpecialServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpecialServicesRepository extends JpaRepository<SpecialServices, Integer> {
    List<SpecialServices> findAllByIsDeletedFalse();

    Optional<SpecialServices> findByIdAndIsDeletedFalse(Integer id);
}

