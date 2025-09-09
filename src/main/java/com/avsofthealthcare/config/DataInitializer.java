package com.avsofthealthcare.config;

import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) {
		// ✅ Create roles if not exist
		createRoleIfNotExists("ADMIN");
		createRoleIfNotExists("USER");
		createRoleIfNotExists("STAFF");
		createRoleIfNotExists("DOCTOR");
		createRoleIfNotExists("PATIENT");

		// ✅ Create initial admin user if no users exist
		if (userRepository.count() == 0) {
			User admin = User.builder()
					.email("admin@example.com")
					.phone("1234567890")
					.password(passwordEncoder.encode("Admin@123"))
					.confirmPassword(passwordEncoder.encode("Admin@123"))
					.enabled(true)
					.build();

			roleRepository.findByName("ADMIN").ifPresent(admin::setRole);

			userRepository.save(admin);
			System.out.println("✅ Admin user created: admin@example.com / Admin@123");
		}
	}

	private void createRoleIfNotExists(String roleName) {
		roleRepository.findByName(roleName).orElseGet(() ->
				roleRepository.save(
						Role.builder()
								.name(roleName)
								.createdBy("SYSTEM")
								.build()
				)
		);
	}
}
