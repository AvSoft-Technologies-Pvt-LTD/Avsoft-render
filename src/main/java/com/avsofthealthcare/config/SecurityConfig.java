package com.avsofthealthcare.config;

import com.avsofthealthcare.security.JwtAuthenticationFilter;
import com.avsofthealthcare.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import java.util.List;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	// ✅ Register Password Encoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// ✅ Expose UserDetailsService (Spring Security picks it automatically)
	@Bean
	public UserDetailsService userDetailsService() {
		return customUserDetailsService;
	}

	// ✅ Authentication Manager (no need for DaoAuthenticationProvider)
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	// ✅ Security Filter Chain
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(auth -> auth
//						.requestMatchers(
//								"/api/auth/patient/register",
//								"/v3/api-docs/**",
//								"/swagger-ui/**",
//								"/swagger-ui.html",
//								"/swagger-resources/**",
//								"/webjars/**",
//								"/api/auth/**",
//								"/h2-console/**"
//						).permitAll()
//						.anyRequest().authenticated()
//				)

//				.authorizeHttpRequests(auth -> auth
//						.requestMatchers("/api/admin/**").hasRole("ADMIN")
//						.requestMatchers("/api/staff/**").hasAnyRole( "ADMIN")
//						.requestMatchers("/api/auth/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
//						.anyRequest().authenticated()
//				)

//				.authorizeHttpRequests(auth -> auth
//						.requestMatchers("/api/auth/**", "/api/master/**", "/api/staff-permissions/**", "/api/dashboard/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
//						.anyRequest().hasRole("ADMIN")
//				)

				.authorizeHttpRequests(auth -> auth
						// ✅ Public APIs: patient & doctor registration, login, Swagger
						.requestMatchers(
								"/api/auth/patient/register",
								"/api/auth/doctor/register",
								"/api/auth/**",
								"/v3/api-docs/**",
								"/swagger-ui/**",
								"/swagger-ui.html",
								"/swagger-resources/**",
								"/webjars/**"
						).permitAll()

						// ✅ H2 console
						.requestMatchers("/h2-console/**").permitAll()

						// ✅ Any other API requires authentication
						.anyRequest().authenticated()
				)


				.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		// ⚡ allow H2-console access
		http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

		return http.build();
	}

	@Bean
	public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
		var config = new org.springframework.web.cors.CorsConfiguration();
		config.setAllowedOrigins(List.of("http://localhost:5432")); // adjust for frontend
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setAllowCredentials(true);

		var source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}


}
