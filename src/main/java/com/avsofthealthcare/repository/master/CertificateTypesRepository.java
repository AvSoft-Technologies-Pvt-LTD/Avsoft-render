package com.avsofthealthcare.repository.master;

import com.avsofthealthcare.entity.master.CertificateTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CertificateTypesRepository extends JpaRepository<CertificateTypes, Integer> {
    List<CertificateTypes> findAllByIsDeletedFalse();
    Optional<CertificateTypes> findByIdAndIsDeletedFalse(Integer id);
}
