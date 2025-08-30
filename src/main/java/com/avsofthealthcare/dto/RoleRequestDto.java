package com.avsofthealthcare.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RoleRequestDto {
    @NotBlank(message = "admin or user is required")
	private String name;       // Role name: ADMIN, USER, etc.
	private String createdBy;  // Who created the role
}
