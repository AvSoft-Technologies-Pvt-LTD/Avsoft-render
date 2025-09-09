package com.avsofthealthcare.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.service.CustomUserDetailsService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	private final CustomUserDetailsService customUserDetailsService;

	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = authHeader.substring(7);

		try {
			// ✅ Extract userId from JWT subject
			String userId = jwtUtil.extractUserId(token);

			if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				User user = userRepository.findById(Long.parseLong(userId)).orElse(null);

				if (user != null && jwtUtil.validateToken(token, user)) {
					// ✅ Load full user details (role + permissions)
					String identifier = (user.getEmail() != null) ? user.getEmail() : user.getPhone();
					CustomUserDetails userDetails =
							(CustomUserDetails) customUserDetailsService.loadUserByUsername(identifier);

					UsernamePasswordAuthenticationToken authToken =
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);

					log.info("✅ Authenticated user={} | role={} | authorities={}",
							user.getId(),
							user.getRole().getName(),
							userDetails.getAuthorities());
				} else {
					log.warn("❌ User not found or invalid token for userId={}", userId);
				}
			}
		} catch (Exception e) {
			log.error("❌ JWT authentication failed", e);
		}

		filterChain.doFilter(request, response);
	}
}
