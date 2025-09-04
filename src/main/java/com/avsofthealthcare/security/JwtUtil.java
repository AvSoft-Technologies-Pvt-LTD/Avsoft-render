package com.avsofthealthcare.security;

import com.avsofthealthcare.entity.Permission;
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
	public String generateToken(User user, String loginIdentifier, List<Permission> permissions) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("user_id", user.getId());
		claims.put("role", user.getRole().getName());
		claims.put("email", user.getEmail());
		claims.put("phone", user.getPhone());

		// Convert permissions to a list of strings (or any suitable structure)
		List<String> permissionStrings = permissions.stream()
				.map(p -> p.getFormName() + ":" + p.getCanAdd() + "," + p.getCanEdit() + "," + p.getCanView())
				.collect(Collectors.toList());

		// Add permissions list to claims
		claims.put("permissions", permissionStrings);

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

	public String extractRole(String token) {
		return extractClaim(token, claims -> claims.get("role", String.class));
	}
}
