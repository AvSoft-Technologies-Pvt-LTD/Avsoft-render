package com.avsofthealthcare.service.impl.master;

import org.springframework.stereotype.Service;

import com.avsofthealthcare.dto.master.PermissionRequestDto;
import com.avsofthealthcare.dto.master.PermissionResponseDto;
import com.avsofthealthcare.entity.master.Permission;
import com.avsofthealthcare.mapper.master.PermissionMapper;
import com.avsofthealthcare.repository.master.PermissionRepository;
import com.avsofthealthcare.service.master.PermissionService;

import java.util.List;

import lombok.RequiredArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

	private final PermissionRepository repository;
	private final PermissionMapper mapper;

	@Override
	public PermissionResponseDto create(PermissionRequestDto dto) {
		Permission saved = repository.save(mapper.toEntity(dto));
		return mapper.toResponseDto(saved);
	}

	@Override
	public List<PermissionResponseDto> getAll() {
		return repository.findAll()
				.stream()
				.map(mapper::toResponseDto)
				.toList();
	}

	@Override
	public PermissionResponseDto update(Long id, PermissionRequestDto dto) {
		Permission entity = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Permission not found with ID: " + id));

		entity.setFormName(dto.getFormName());
		entity.setAction(dto.getAction());

		return mapper.toResponseDto(repository.save(entity));
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<PermissionResponseDto> getPermissionsByRoleId(Long roleId) {
		return repository.findByRole_Id(roleId)
				.stream()
				.map(mapper::toResponseDto)
				.toList();
	}

	@Override
	public List<PermissionResponseDto> getPermissionsByRoleName(String roleName) {
		return repository.findByRole_Name(roleName)
				.stream()
				.map(mapper::toResponseDto)
				.toList();
	}
}
