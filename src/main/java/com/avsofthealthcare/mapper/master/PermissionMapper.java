package com.avsofthealthcare.mapper.master;



import org.springframework.stereotype.Component;

import com.avsofthealthcare.dto.master.PermissionDto;
import com.avsofthealthcare.entity.master.Permission;


@Component
public class PermissionMapper {
	public PermissionDto toDto(Permission entity) {
		return PermissionDto.builder()
				.id(entity.getId())
				.formName(entity.getFormName())
				.action(entity.getAction())
				.build();
	}

	public Permission toEntity(PermissionDto dto) {
		Permission p = new Permission();
		p.setId(dto.getId());
		p.setFormName(dto.getFormName());
		p.setAction(dto.getAction());
		return p;
	}
}
