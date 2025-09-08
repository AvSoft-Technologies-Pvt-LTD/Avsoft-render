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
		byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// ✅ Extract userId (we set as subject)
	public Long extractUserId(String token) {
		return Long.valueOf(extractClaim(token, Claims::getSubject));
	}

	// ✅ Extract email
	public String extractEmail(String token) {
		return extractClaim(token, claims -> claims.get("email", String.class));
	}

	// ✅ Extract role
	public String extractRole(String token) {
		return extractClaim(token, claims -> claims.get("role", String.class));
	}

	public String extractIdentifier(String token) {
		return extractClaim(token, claims -> claims.get("identifier", String.class));
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

	public Boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}

	// ✅ Generate JWT with permissions
	public String generateToken(User user, List<String> permissions) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("user_id", user.getId());
		claims.put("role", user.getRole().getName());
		claims.put("email", user.getEmail());
		claims.put("phone", user.getPhone());
		claims.put("permissions", permissions);

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(user.getEmail()) // email as subject
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSigningKey())
				.compact();
	}



	// ✅ Validate token
	public Boolean validateToken(String token, org.springframework.security.core.userdetails.UserDetails userDetails) {
		Long userId = extractUserId(token);
		return (userId != null && !isTokenExpired(token));
	}
}
