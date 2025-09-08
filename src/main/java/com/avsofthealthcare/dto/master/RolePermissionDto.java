package com.avsofthealthcare.dto.master;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionDto {
	private Long id;
	private Long roleId;
	private Long permissionId;
}

