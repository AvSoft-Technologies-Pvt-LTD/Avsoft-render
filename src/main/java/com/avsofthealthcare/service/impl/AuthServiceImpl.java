package com.avsofthealthcare.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.avsofthealthcare.dto.AuthRequest;
import com.avsofthealthcare.dto.AuthResponse;
import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffPermission;
import com.avsofthealthcare.entity.master.Permission;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.repository.RoleRepository;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffDetailsRepository;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffPermissionRepository;
import com.avsofthealthcare.repository.master.PermissionRepository;
import com.avsofthealthcare.security.JwtUtil;
import com.avsofthealthcare.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final StaffDetailsRepository staffDetailsRepository;
	private final RoleRepository roleRepository;
	private final PermissionRepository permissionRepository;
	private final StaffPermissionRepository staffPermissionRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@Override
	public AuthResponse login(AuthRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getIdentifier(),
						request.getPassword()
				)
		);

		User user = request.getIdentifier().contains("@")
				? userRepository.findByEmail(request.getIdentifier()).orElse(null)
				: userRepository.findByPhone(request.getIdentifier()).orElse(null);

		if (user == null) {
			StaffDetails staff = request.getIdentifier().contains("@")
					? staffDetailsRepository.findByEmailId(request.getIdentifier()).orElse(null)
					: staffDetailsRepository.findByPhoneNumber(request.getIdentifier()).orElse(null);

			if (staff == null) throw new ResourceNotFoundException("User not found");

			user = staff.getUser();
			if (user == null) {
				throw new ResourceNotFoundException("Staff has no linked user account");
			}
		}

		List<String> permissionStrings = staffDetailsRepository.findByUser(user)
				.map(staff -> staffPermissionRepository.findByStaff_Id(staff.getId()))
				.orElse(List.of()) // no staff record
				.stream()
				.map(sp -> sp.getPermission().getFormName() + ":" + sp.getPermission().getAction())
				.toList();

		String token = jwtUtil.generateToken(user, permissionStrings);

		return AuthResponse.builder()
				.token(token)
				.userId(user.getId())
				.email(user.getEmail())
				.phone(user.getPhone())
				.role(user.getRole().getName())
				.permissions(permissionStrings)
				.message("Login successful")
				.build();
	}
}
