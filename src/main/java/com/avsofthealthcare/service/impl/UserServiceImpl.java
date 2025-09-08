package com.avsofthealthcare.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avsofthealthcare.dto.AuthResponse;
import com.avsofthealthcare.dto.UserUpdateRequest;
import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffPermission;
import com.avsofthealthcare.entity.master.Permission;
import com.avsofthealthcare.entity.master.RolePermission;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffDetailsRepository;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffPermissionRepository;
import com.avsofthealthcare.security.JwtUtil;
import com.avsofthealthcare.service.UserService;
import com.avsofthealthcare.validation.UserValidationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserValidationService userValidationService;
	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;
	private final StaffDetailsRepository staffDetailsRepository;
	private final StaffPermissionRepository staffPermissionRepository;

	@Override
	public AuthResponse login(String identifier, String password) {
		User user = userRepository.findByEmailOrPhoneWithPermissions(identifier)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + identifier));

		if (!passwordEncoder.matches(password, user.getPassword())) {
			return AuthResponse.builder().message("Invalid credentials").build();
		}

		Role role = user.getRole();

		List<Permission> permissions = new ArrayList<>();
		Optional<StaffDetails> staffOpt = staffDetailsRepository.findByUser(user);

		if (staffOpt.isPresent()) {
			StaffDetails staff = staffOpt.get();
			permissions = staffPermissionRepository.findByStaff_Id(staff.getId())
					.stream()
					.map(StaffPermission::getPermission)
					.toList();
		}

		if (permissions.isEmpty()) {
			permissions = role.getRolePermissions().stream()
					.map(RolePermission::getPermission)
					.toList();
		}

		List<String> permissionStrings = permissions.stream()
				.map(p -> p.getFormName() + ":" + p.getAction())
				.toList();

		String token = jwtUtil.generateToken(user, permissionStrings);

		return AuthResponse.builder()
				.token(token)
				.userId(user.getId())
				.email(user.getEmail())
				.phone(user.getPhone())
				.role(role.getName())
				.permissions(permissionStrings)
				.message("Login successful")
				.build();
	}

	@Transactional
	public String updateUser(UserUpdateRequest req) {
		userValidationService.validateUniqueEmailAndPhoneForUpdate(
				req.getUserId(),
				req.getEmail(),
				req.getPhone()
		);

		User user = userRepository.findById(req.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		user.setEmail(req.getEmail());
		user.setPhone(req.getPhone());

		if (req.getPassword() != null && !req.getPassword().isBlank()) {
			user.setPassword(passwordEncoder.encode(req.getPassword()));
		}

		userRepository.save(user);

		return "User updated successfully";
	}
}
