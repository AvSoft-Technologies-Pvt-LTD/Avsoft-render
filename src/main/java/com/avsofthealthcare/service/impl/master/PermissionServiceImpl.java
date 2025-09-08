package com.avsofthealthcare.service.impl.master;

import org.springframework.stereotype.Service;

import com.avsofthealthcare.dto.master.PermissionDto;
import com.avsofthealthcare.entity.master.Permission;
import com.avsofthealthcare.mapper.master.PermissionMapper;
import com.avsofthealthcare.repository.master.PermissionRepository;
import com.avsofthealthcare.service.master.PermissionService;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
	private final PermissionRepository repository;
	private final PermissionMapper mapper;

	@Override
	public PermissionDto create(PermissionDto dto) {
		Permission saved = repository.save(mapper.toEntity(dto));
		return mapper.toDto(saved);
	}

	@Override
	public List<PermissionDto> getAll() {
		return repository.findAll().stream().map(mapper::toDto).toList();
	}

	@Override
	public PermissionDto update(Long id, PermissionDto dto) {
		Permission entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Permission not found"));
		entity.setFormName(dto.getFormName());
		entity.setAction(dto.getAction());
		return mapper.toDto(repository.save(entity));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<PermissionDto> getPermissionsByRoleId(Long roleId) {
		return repository.findByRole_Id(roleId).stream()
				.map(mapper::toDto)
				.toList();
	}

	@Override
	public List<PermissionDto> getPermissionsByRoleName(String roleName) {
		return repository.findByRole_Name(roleName).stream()
				.map(mapper::toDto)
				.toList();
	}

}