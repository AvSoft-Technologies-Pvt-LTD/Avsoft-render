package com.avsofthealthcare.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("permissionChecker")
public class PermissionChecker {

	private static final Logger log = LoggerFactory.getLogger(PermissionChecker.class);

	public boolean hasPermission(String formName, String action) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
			log.warn("❌ No authenticated user found or invalid principal");
			return false;
		}

		String role = userDetails.getUser().getRole() != null
				? userDetails.getUser().getRole().getName().toUpperCase()
				: null;

		List<String> permissions = userDetails.getPermissions();

		log.info("🔎 Checking permission for user={} | Role={} | Required={}:{} | UserPermissions={}",
				userDetails.getUsername(), role, formName, action, permissions);

		// 1️⃣ ADMIN → full access
		if ("ADMIN".equals(role)) {
			log.info("✅ Access granted via ADMIN role");
			return true;
		}

		// 2️⃣ PATIENT → only patients APIs
		if ("PATIENT".equals(role)) {
			if ("patients".equalsIgnoreCase(formName)) {
				log.info("✅ Access granted via PATIENT role for patients APIs");
				return true;
			} else {
				log.warn("❌ Patient role tried accessing non-patient API: {}", formName);
				return false;
			}
		}

		// 3️⃣ Staff roles (DOCTOR, NURSE, FRONTDESK, etc.) → explicit permission required
		if (permissions != null && permissions.contains(formName + ":" + action)) {
			log.info("✅ Access granted via explicit staff permission");
			return true;
		}

		// ❌ Default deny
		log.warn("❌ Access denied for user={} | Role={} | Required={}:{}",
				userDetails.getUsername(), role, formName, action);
		return false;
	}
}

