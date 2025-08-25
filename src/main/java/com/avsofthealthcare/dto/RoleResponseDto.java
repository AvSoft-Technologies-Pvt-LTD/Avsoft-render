package com.avsofthealthcare.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoleResponseDto {
	private Long id;
	private String name;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;
}