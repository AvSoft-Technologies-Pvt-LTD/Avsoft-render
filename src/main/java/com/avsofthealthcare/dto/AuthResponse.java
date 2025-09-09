package com.avsofthealthcare.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	private String token;
	private Long userId;
	private String email;
	private String phone;
	private String role;
	private String message;

	private List<String> permissions;
}
