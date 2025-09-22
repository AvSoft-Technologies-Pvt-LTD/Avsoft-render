package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByPatientId(Long patientId);
    void deleteByPatientId(Long patientId);
}
