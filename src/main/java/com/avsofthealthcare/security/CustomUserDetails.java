package com.avsofthealthcare.security;

import com.avsofthealthcare.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
public class CustomUserDetails implements UserDetails {

	private final User user;
	private final List<String> permissions;
	private final Set<GrantedAuthority> authorities;

	public CustomUserDetails(User user, List<String> permissions, Set<GrantedAuthority> authorities) {
		this.user = user;
		this.permissions = permissions;
		this.authorities = authorities;
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
