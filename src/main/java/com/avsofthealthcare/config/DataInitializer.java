package com.avsofthealthcare.config;

import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        // Create initial admin user
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setPhone("1234567890");
            admin.setPassword(passwordEncoder.encode("Admin@1234"));
            admin.setConfirmPassword(passwordEncoder.encode("Admin@1234"));

            // Assign ADMIN role
            Optional<Role> adminRole = roleRepository.findByName("ADMIN");
            Set<Role> roles = new HashSet<>();
            adminRole.ifPresent(roles::add);
            admin.setRoles(roles);

            userRepository.save(admin);
            System.out.println("Admin user created: admin@example.com / Admin@1234");
        }
    }
}
