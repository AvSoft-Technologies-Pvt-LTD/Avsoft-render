package com.avsofthealthcare.security;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component("permissionChecker")
@RequiredArgsConstructor
public class PermissionChecker {

	private final JwtUtil jwtUtil;

	public boolean hasPermission(String formName, String action) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !(authentication.getCredentials() instanceof String token)) {
			return false;
		}

		// ✅ Extract permissions directly from JWT
		List<String> permissions = jwtUtil.extractPermissions(token);

		String requiredPermission = formName + ":" + action;
		return permissions != null && permissions.contains(requiredPermission);
	}
}
