package com.avsofthealthcare.security;

import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	private SecretKey getSigningKey() {
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// 🔹 Extract username (email in your case)
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	// 🔹 Generic claim extractor
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	// 🔹 Parse all claims
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	// 🔹 Check expiry
	public Boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}

	// 🔹 Generate token for your custom User entity
	public String generateToken(User user, String loginIdentifier) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("user_id", user.getId());
		claims.put("roles", user.getRoles().stream()
				.map(Role::getName)
				.toList());
		claims.put("email", user.getEmail());
		claims.put("phone", user.getPhone());

		// Subject = login identifier (phone or email based on login)
		return createToken(claims, loginIdentifier);
	}

	// 🔹 Build the token
	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject) // email is subject
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration)) // Use configured expiration
				.signWith(getSigningKey())
				.compact();
	}

	// 🔹 Validate token
	public Boolean validateToken(String token, User user) {
		final String username = extractUsername(token);
		return (username.equals(user.getEmail()) && !isTokenExpired(token));
	}

	// 🔹 Helper methods to extract extra info
	public Long extractUserId(String token) {
		return extractClaim(token, claims -> claims.get("user_id", Long.class));
	}

	public List<String> extractRoles(String token) {
		return extractClaim(token, claims -> claims.get("roles", List.class));
	}
}
