package com.avsofthealthcare.avsofthealthcare.controller;

import com.avsofthealthcare.avsofthealthcare.dto.LoginRequest;
import com.avsofthealthcare.avsofthealthcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String result = authService.login(request.getIdentifier(), request.getPassword());
        return ResponseEntity.ok(result);
    }
}