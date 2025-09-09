package com.avsofthealthcare.mapper.master;



import org.springframework.stereotype.Component;

import com.avsofthealthcare.dto.master.PermissionRequestDto;
import com.avsofthealthcare.dto.master.PermissionResponseDto;
import com.avsofthealthcare.entity.master.Permission;


import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

	public Permission toEntity(PermissionRequestDto dto) {
		Permission permission = new Permission();
		permission.setFormName(dto.getFormName());
		permission.setAction(dto.getAction());
		return permission;
	}

	public PermissionResponseDto toResponseDto(Permission entity) {
		return PermissionResponseDto.builder()
				.id(entity.getId())
				.formName(entity.getFormName())
				.action(entity.getAction())
				.build();
	}
}

