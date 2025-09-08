package com.avsofthealthcare.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffDetailsRepository;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffPermissionRepository;
import com.avsofthealthcare.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	private final StaffDetailsRepository staffDetailsRepository;
	private final StaffPermissionRepository staffPermissionRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(identifier)
				.or(() -> userRepository.findByPhone(identifier))
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + identifier));

		StaffDetails staff = staffDetailsRepository.findByUser(user)
				.orElseThrow(() -> new UsernameNotFoundException("Staff not found for user: " + user.getId()));

		List<String> permissions = staffPermissionRepository.findByStaffIdAndActiveTrue(staff.getId()).stream()
				.map(sp -> sp.getPermission().getFormName() + ":" + sp.getPermission().getAction())
				.toList();

		Set<GrantedAuthority> authorities = permissions.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toSet());

		return new CustomUserDetails(user, permissions, authorities);
	}
}
