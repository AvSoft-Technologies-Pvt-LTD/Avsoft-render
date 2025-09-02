package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.AuthRequest;
import com.avsofthealthcare.dto.AuthResponse;
import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.repository.RoleRepository;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffRepository;
import com.avsofthealthcare.security.JwtUtil;
import com.avsofthealthcare.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final StaffRepository staffRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
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
				? userRepository.findByEmail(request.getIdentifier()).orElse(null)
				: userRepository.findByPhone(request.getIdentifier()).orElse(null);

		// ✅ Step 2b: Fallback to staff_details and upsert a User if needed
		if (user == null) {
			StaffDetails staff = request.getIdentifier().contains("@")
					? staffRepository.findByEmailId(request.getIdentifier()).orElse(null)
					: staffRepository.findByPhoneNumber(request.getIdentifier()).orElse(null);

			if (staff == null) {
				throw new ResourceNotFoundException("User not found");
			}

			Role staffRole = roleRepository.findByName("STAFF")
					.orElseThrow(() -> new ResourceNotFoundException("Role 'STAFF' not found"));

			String encoded = passwordEncoder.encode(request.getPassword());

			if (staff.getUserId() != null) {
				user = userRepository.findById(staff.getUserId()).orElse(null);
			}
			if (user == null) {
				user = User.builder()
						.email(staff.getEmailId())
						.phone(staff.getPhoneNumber())
						.password(encoded)
						.confirmPassword(encoded)
						.role(staffRole)
						.enabled(true)
						.build();
			} else {
				user.setEmail(staff.getEmailId());
				user.setPhone(staff.getPhoneNumber());
				user.setPassword(encoded);
				user.setConfirmPassword(encoded);
				user.setRole(staffRole);
			}
			user = userRepository.save(user);
			if (staff.getUserId() == null || !staff.getUserId().equals(user.getId())) {
				staff.setUserId(user.getId());
				staffRepository.save(staff);
			}
		}

		// ✅ Step 3: Generate JWT → pass the whole User entity
		String token = jwtUtil.generateToken(user, request.getIdentifier());

		// ✅ Step 4: Return token + role
		return AuthResponse.builder()
				.token(token)
				.userId(user.getId())
				.email(user.getEmail())
				.phone(user.getPhone())
				.role(user.getRole().getName())
				.message("Login successful")
				.build();
	}
}

