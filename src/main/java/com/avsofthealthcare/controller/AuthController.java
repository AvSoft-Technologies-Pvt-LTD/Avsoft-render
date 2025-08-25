package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.AuthResponse;
import com.avsofthealthcare.dto.LoginRequest;
import com.avsofthealthcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsCons
public class AuthController {


    private UserService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // UserService will authenticate and generate a JWT
        return ResponseEntity.ok(userService.login(request.getIdentifier(), request.getPassword()));
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/test")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request.getIdentifier(), request.getPassword());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok("Authentication successful! User: " + authentication.getName());
        }
        return ResponseEntity.ok("No authentication found");
    }
}