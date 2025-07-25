package com.avsofthealthcare.avsofthealthcare.dto;


import lombok.Data;

@Data
public class LoginRequest {
    private String identifier; // email or phone
    private String password;
}
