package com.avsofthealthcare.security;

import com.avsofthealthcare.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	// ✅ Extract userId from subject
	public String extractUserId(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	// ✅ Extract email if needed
	public String extractEmail(String token) {
		return extractClaim(token, claims -> claims.get("email", String.class));
	}

	// ✅ Extract phone if needed
	public String extractPhone(String token) {
		return extractClaim(token, claims -> claims.get("phone", String.class));
	}

	// ✅ Extract role
	public String extractRole(String token) {
		return extractClaim(token, claims -> claims.get("role", String.class));
	}

	// ✅ Extract permissions
	@SuppressWarnings("unchecked")
	public List<String> extractPermissions(String token) {
		return extractClaim(token, claims -> (List<String>) claims.get("permissions"));
	}

	// ✅ Generic claim extractor
	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		final Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	// ✅ Check token expiry
	public boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}

	// ✅ Generate JWT (userId as subject)
	public String generateToken(User user, List<String> permissions) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", user.getRole().getName());
		claims.put("email", user.getEmail());
		claims.put("phone", user.getPhone());
		claims.put("permissions", permissions);

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(String.valueOf(user.getId())) // userId is subject
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	// ✅ Validate token
	public boolean validateToken(String token, User user) {
		String userId = extractUserId(token);
		return (userId.equals(String.valueOf(user.getId())) && !isTokenExpired(token));
	}
}
