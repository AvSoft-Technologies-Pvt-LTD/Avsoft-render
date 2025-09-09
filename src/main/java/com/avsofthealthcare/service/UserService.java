package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.AuthResponse;

public interface UserService {
    AuthResponse login(String identifier, String password);
}