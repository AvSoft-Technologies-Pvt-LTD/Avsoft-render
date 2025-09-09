package com.avsofthealthcare.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return Optional.empty();
		}

		Object principal = authentication.getPrincipal();
		if (principal == null) {
			return Optional.empty();
		}

		// When JwtAuthenticationFilter authenticates, it sets principal to our User entity
		if (principal instanceof com.avsofthealthcare.entity.User) {
			Long userId = ((com.avsofthealthcare.entity.User) principal).getId();
			return Optional.ofNullable(userId).map(String::valueOf);
		}

		// Fallback to the authentication name (e.g., username/email) if principal isn't our User
		return Optional.ofNullable(authentication.getName());
	}
}

