package com.avsofthealthcare.dto;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String password; // Optional: only if changing password
}

