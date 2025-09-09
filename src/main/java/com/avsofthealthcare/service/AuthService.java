package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.AuthRequest;
import com.avsofthealthcare.dto.AuthResponse;

public interface AuthService {
	AuthResponse login(AuthRequest request);
}