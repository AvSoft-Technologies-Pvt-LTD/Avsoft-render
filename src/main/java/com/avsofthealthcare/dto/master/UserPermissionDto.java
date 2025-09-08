package com.avsofthealthcare.dto.master;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO for individual permission assignment
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPermissionDto {
	private Long id;
	private Long staffId;
	private Long permissionId;
	private Boolean allowed;
}
