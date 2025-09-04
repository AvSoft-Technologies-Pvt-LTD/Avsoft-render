package com.avsofthealthcare.service.impl;



import java.util.List;

import com.avsofthealthcare.dto.AuthResponse;
import com.avsofthealthcare.dto.UserUpdateRequest;
import com.avsofthealthcare.entity.Permission;
import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.security.JwtUtil;
import com.avsofthealthcare.service.UserService;
import com.avsofthealthcare.validation.UserValidationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidationService userValidationService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(String identifier, String password) {
        User user = identifier.contains("@") ?
                userRepository.findByEmail(identifier).orElse(null) :
                userRepository.findByPhone(identifier).orElse(null);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Generate JWT token
	        List<Permission> permissions = user.getRole().getPermissions();

	        // Generate JWT token with permissions
	        String token = jwtUtil.generateToken(user, identifier, permissions);
            
            return AuthResponse.builder()
                    .token(token)
                    .userId(user.getId())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .role(user.getRole().getName())
                    .message("Login successful")
                    .build();
        }
        
        return AuthResponse.builder()
                .message("Invalid credentials")
                .build();
    }

    @Transactional
    public String updateUser(UserUpdateRequest req) {
        // ✅ Step 1: Validate email & phone uniqueness (excluding current user)
        userValidationService.validateUniqueEmailAndPhoneForUpdate(
                req.getUserId(),
                req.getEmail(),
                req.getPhone()
        );

        // ✅ Step 2: Find the user
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // ✅ Step 3: Update fields
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());

        // If password is being updated (optional)
        if (req.getPassword() != null && !req.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(req.getPassword())); // Hash before saving
        }

        // ✅ Step 4: Save
        userRepository.save(user);

        return "User updated successfully";
    }
}
