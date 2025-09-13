package com.avsofthealthcare.dto.master;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionBulkRequestDto {

	private Long roleId;
	private List<Long> permissionIds;
}
