package com.avsofthealthcare.service;

import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffPermission;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffDetailsRepository;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffPermissionRepository;
import com.avsofthealthcare.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	private final StaffDetailsRepository staffDetailsRepository;
	private final StaffPermissionRepository staffPermissionRepository;

	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
		User user;

		// 1️⃣ Try to resolve by userId (from JWT)
		if (identifier.matches("\\d+")) {
			Long userId = Long.valueOf(identifier);
			user = userRepository.findById(userId)
					.orElseThrow(() -> new UsernameNotFoundException("User not found by ID: " + userId));
		}
		// 2️⃣ Otherwise, resolve by email or phone (login flow)
		else {
			user = userRepository.findByEmail(identifier)
					.or(() -> userRepository.findByPhone(identifier))
					.orElseThrow(() -> new UsernameNotFoundException("User not found by email/phone: " + identifier));
		}

		// 3️⃣ Load staff permissions (only if user has staffDetails)
		List<String> permissions = staffDetailsRepository.findByUser(user)
				.map(staff -> staffPermissionRepository.findByStaffIdAndActiveTrue(staff.getId())
						.stream()
						.map(sp -> sp.getPermission().getFormName() + ":" + sp.getPermission().getAction())
						.toList())
				.orElse(List.of());

		// 4️⃣ Build authorities = role + permissions
		Set<GrantedAuthority> authorities = new HashSet<>();
		permissions.forEach(p -> authorities.add(new SimpleGrantedAuthority(p)));

		if (user.getRole() != null && user.getRole().getName() != null) {
			String roleName = user.getRole().getName().trim().toUpperCase();
			authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
		} else {
			throw new UsernameNotFoundException("User role is missing: " + identifier);
		}

		log.info("Loaded User={} | Role={} | Permissions={} | Authorities={}",
				user.getId(), user.getRole().getName(), permissions, authorities);

		return new CustomUserDetails(user, permissions, authorities);
	}
}
