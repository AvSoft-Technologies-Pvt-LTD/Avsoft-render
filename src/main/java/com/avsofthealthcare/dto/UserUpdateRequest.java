package com.avsofthealthcare.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserUpdateRequest {
    @NotNull(message = "userid is required")
    @Positive(message = "id must be greater than 0")
    private Long userId;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "enter a valid email")
    private String email;
    @NotBlank(message = "phone no is required")
    @Pattern(regexp = "^[0-9]{10}$",message = "phone number must be 10 digit number")
    private String phone;
    @Size(min = 8,max = 20,message = "password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])$",message = "password must contain atleast one digit,one lowercase,one uppercase,and a special character")
    private String password; // Optional: only if changing password
}

