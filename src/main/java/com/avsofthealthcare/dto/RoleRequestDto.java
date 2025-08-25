package com.avsofthealthcare.dto;


import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RoleRequestDto {
	private String name;       // Role name: ADMIN, USER, etc.
	private String createdBy;  // Who created the role
}
