package com.avsofthealthcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "email or phone is required")
    @Pattern(regexp = "^(?:[0-9]{10}|[A-Za-z0-9+_.-]+@[A-Za-z0-9+.-]+)$",message = "must be a valid email or a 10 digit phone number")
	private String identifier; // can be email or phone
    @NotBlank(message = "password is required")
    @Size(min=8,max=20,message = "password must be between 8 and 20 characters")
    @Pattern(  regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,20}$",
            message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character")
	private String password;
}
