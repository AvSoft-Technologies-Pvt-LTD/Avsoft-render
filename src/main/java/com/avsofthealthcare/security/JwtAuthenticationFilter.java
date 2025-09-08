package com.avsofthealthcare.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.avsofthealthcare.service.CustomUserDetailsService;
import com.avsofthealthcare.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final CustomUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		String jwtToken = null;
		String identifier = null;

		// ✅ Extract token
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			jwtToken = authHeader.substring(7);
			try {
				// Extract login identifier (email or phone)
				identifier = jwtUtil.extractIdentifier(jwtToken);
			} catch (Exception e) {
				logger.warn("Invalid JWT token: " + e.getMessage());
			}
		}


		// ✅ Authenticate if not already authenticated
		if (identifier != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(identifier);

				if (jwtUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken authToken =
							new UsernamePasswordAuthenticationToken(
									userDetails,
									null,
									userDetails.getAuthorities() // Roles are automatically included
							);
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);

					System.out.println("Auth set: " + SecurityContextHolder.getContext().getAuthentication());

				}
			} catch (Exception e) {
				logger.warn("Failed to authenticate user from JWT: " + e.getMessage());
			}
		}

		filterChain.doFilter(request, response);
	}



}
