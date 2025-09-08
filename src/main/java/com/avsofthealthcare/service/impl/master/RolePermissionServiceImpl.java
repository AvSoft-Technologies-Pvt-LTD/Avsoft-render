package com.avsofthealthcare.service.impl.master;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avsofthealthcare.dto.master.RolePermissionDto;
import com.avsofthealthcare.mapper.master.RolePermissionMapper;
import com.avsofthealthcare.repository.RolePermissionRepository;
import com.avsofthealthcare.service.master.RolePermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {
	private final RolePermissionRepository repository;
	private final RolePermissionMapper mapper;

	@Override
	public RolePermissionDto assign(RolePermissionDto dto) {
		return mapper.toDto(repository.save(mapper.toEntity(dto)));
	}

	@Override
	public List<RolePermissionDto> getByRole(Long roleId) {
		return repository.findByRoleId(roleId).stream().map(mapper::toDto).toList();
	}

	@Override
	public void revoke(Long id) {
		repository.deleteById(id);
	}
}
