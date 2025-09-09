package com.avsofthealthcare.dto;


import lombok.Data;

@Data
public class RoleUpdateDto {
	private String name;        // New name if updating
	private String updatedBy;   // Who updated the role
}
