package com.avsofthealthcare.dto;

import lombok.Data;

@Data
public class AuthRequest {
	private String identifier; // can be email or phone
	private String password;
}
