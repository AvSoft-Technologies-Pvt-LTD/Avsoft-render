package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.AuthRequest;
import com.avsofthealthcare.dto.AuthResponse;
import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.security.JwtUtil;
import com.avsofthealthcare.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;

	@Override
	public AuthResponse login(AuthRequest request) {
		// ✅ Step 1: Authenticate using Spring Security
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getIdentifier(),
						request.getPassword()
				)
		);

		// ✅ Step 2: Fetch actual User entity from DB (by email or phone)
		User user = request.getIdentifier().contains("@")
				? userRepository.findByEmail(request.getIdentifier())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"))
				: userRepository.findByPhone(request.getIdentifier())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		// ✅ Step 3: Extract roles
		List<String> roleNames = user.getRoles()
				.stream()
				.map(role -> role.getName())
				.toList();

		// ✅ Step 4: Generate JWT → pass the whole User entity
		String token = jwtUtil.generateToken(user, request.getIdentifier());

		// ✅ Step 5: Return token + roles
		return AuthResponse.builder()
				.token(token)
				.userId(user.getId())
				.email(user.getEmail())
				.phone(user.getPhone())
				.roles(roleNames)
				.message("Login successful")
				.build();
	}
}

