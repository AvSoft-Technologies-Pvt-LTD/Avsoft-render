package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

    boolean existsByEmailAndIdNot(String email, Long id);
    boolean existsByPhoneAndIdNot(String phone, Long id);

	@Query("SELECT u FROM User u " +
			"JOIN FETCH u.role r " +
			"JOIN FETCH r.rolePermissions rp " +
			"JOIN FETCH rp.permission " +
			"WHERE u.email = :identifier OR u.phone = :identifier")
	Optional<User> findByEmailOrPhoneWithPermissions(@Param("identifier") String identifier);


}
