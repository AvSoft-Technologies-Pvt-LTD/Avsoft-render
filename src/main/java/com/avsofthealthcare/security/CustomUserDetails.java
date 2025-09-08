package com.avsofthealthcare.security;

import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffPermission;
import com.avsofthealthcare.entity.master.UserPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

	private final User user;
	private final List<String> permissions;   // e.g., ["patients:VIEW", "patients:ADD"]
	private final Set<GrantedAuthority> authorities;

	// ✅ Single constructor
	public CustomUserDetails(User user, List<String> permissions, Set<GrantedAuthority> authorities) {
		this.user = user;
		this.permissions = permissions;
		this.authorities = authorities;
	}

	// ✅ Factory method: build from UserPermission entities (role-based)
	public static CustomUserDetails fromUserPermissions(User user, List<UserPermission> userPermissions, Set<GrantedAuthority> authorities) {
		List<String> perms = userPermissions.stream()
				.map(up -> up.getPermission().getFormName() + ":" + up.getPermission().getAction())
				.toList();
		return new CustomUserDetails(user, perms, authorities);
	}

	// ✅ Factory method: build from StaffPermission entities (staff-based)
	public static CustomUserDetails fromStaffPermissions(User user, List<StaffPermission> staffPermissions, Set<GrantedAuthority> authorities) {
		List<String> perms = staffPermissions.stream()
				.map(sp -> sp.getPermission().getFormName() + ":" + sp.getPermission().getAction())
				.toList();
		return new CustomUserDetails(user, perms, authorities);
	}

	// 🔹 Expose User if needed
	public User getUser() {
		return user;
	}

	// 🔹 Expose permissions
	public List<String> getPermissions() {
		return permissions;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail() != null ? user.getEmail() : user.getPhone();
	}

	@Override
	public boolean isAccountNonExpired() { return true; }

	@Override
	public boolean isAccountNonLocked() { return true; }

	@Override
	public boolean isCredentialsNonExpired() { return true; }

	@Override
	public boolean isEnabled() { return user.isEnabled(); }
}
