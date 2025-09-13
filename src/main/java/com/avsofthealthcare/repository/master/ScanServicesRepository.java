package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.ScanServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScanServicesRepository extends JpaRepository<ScanServices, Integer> {
    List<ScanServices> findAllByIsDeletedFalse();

    Optional<ScanServices> findByIdAndIsDeletedFalse(Integer id);
}