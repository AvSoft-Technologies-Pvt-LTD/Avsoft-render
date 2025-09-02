package com.avsofthealthcare.service;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
		// Try to find by email first, then by phone
		User user = userRepository.findByEmail(identifier)
				.or(() -> userRepository.findByPhone(identifier))
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email or phone: " + identifier));

		// Convert single user role into Spring Security authority
		Set<GrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));

		// Return Spring Security UserDetails
		return new org.springframework.security.core.userdetails.User(
				identifier,                    // Use login identifier (email or phone)
				user.getPassword(),            // Password from DB
				user.isEnabled(),              // Account enabled
				true,                          // Account non-expired
				true,                          // Credentials non-expired
				true,                          // Account non-locked
				authorities                    // Roles as authorities
		);
	}
}