package com.avsofthealthcare.repository.master;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avsofthealthcare.entity.master.Designation;

public interface DesignationRepository extends JpaRepository<Designation, Long> {
	Optional<Designation> findByName(String name);
}
