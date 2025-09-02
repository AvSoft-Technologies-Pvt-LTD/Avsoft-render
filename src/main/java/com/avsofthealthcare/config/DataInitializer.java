package com.avsofthealthcare.config;

import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) {
		// Create roles if not exist
		if (roleRepository.findByName("ADMIN").isEmpty()) {
			roleRepository.save(new Role(null, "ADMIN", null, null, "SYSTEM", null));
		}
		if (roleRepository.findByName("USER").isEmpty()) {
			roleRepository.save(new Role(null, "USER", null, null, "SYSTEM", null));
		}
		if (roleRepository.findByName("STAFF").isEmpty()) {
			roleRepository.save(new Role(null, "STAFF", null, null, "SYSTEM", null));
		}
		if (roleRepository.findByName("DOCTOR").isEmpty()) {
			roleRepository.save(new Role(null, "DOCTOR", null, null, "SYSTEM", null));
		}
		if (roleRepository.findByName("PATIENT").isEmpty()) {
			roleRepository.save(new Role(null, "PATIENT", null, null, "SYSTEM", null));
		}

		// Create initial admin user
		if (userRepository.count() == 0) {
			User admin = new User();
			admin.setEmail("admin@example.com");
			admin.setPhone("1234567890");
			admin.setPassword(passwordEncoder.encode("admin123"));
			admin.setConfirmPassword(passwordEncoder.encode("admin123"));

			// Assign ADMIN role
			Optional<Role> adminRole = roleRepository.findByName("ADMIN");
			adminRole.ifPresent(admin::setRole);

			userRepository.save(admin);
			System.out.println("Admin user created: admin@example.com / admin123");
		}
	}
}
