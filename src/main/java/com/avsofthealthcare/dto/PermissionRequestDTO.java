package com.avsofthealthcare.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PermissionRequestDTO {
	@NotNull(message = "Role ID cannot be null")
	private Long roleId;

	@NotNull(message = "Form name cannot be null")
	private String formName;

	@NotNull(message = "canAdd cannot be null")
	private Boolean canAdd;

	@NotNull(message = "canEdit cannot be null")
	private Boolean canEdit;

	@NotNull(message = "canView cannot be null")
	private Boolean canView;
}