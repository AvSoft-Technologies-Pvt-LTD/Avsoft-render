package com.avsofthealthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.avsofthealthcare.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
	boolean existsByName(String name);
	Optional<Role> findById(Long id);
	List<Role> findAll();

}
