package com.avsofthealthcare.dto;

import lombok.Data;

@Data
public class PermissionResponseDTO {
	private Long id;
	private Long roleId;
	private String roleName; // optional, if you want to include role name
	private String formName;
	private Boolean canAdd;
	private Boolean canEdit;
	private Boolean canView;
}