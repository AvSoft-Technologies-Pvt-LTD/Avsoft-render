package com.avsofthealthcare.service.impl.master;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avsofthealthcare.dto.master.RolePermissionBulkRequestDto;
import com.avsofthealthcare.dto.master.RolePermissionRequestDto;
import com.avsofthealthcare.dto.master.RolePermissionResponseDto;
import com.avsofthealthcare.entity.master.RolePermission;
import com.avsofthealthcare.mapper.master.RolePermissionMapper;
import com.avsofthealthcare.repository.RolePermissionRepository;
import com.avsofthealthcare.service.master.RolePermissionService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {

	private final RolePermissionRepository repository;
	private final RolePermissionMapper mapper;

	@Override
	public RolePermissionResponseDto assign(RolePermissionRequestDto dto) {
		RolePermission saved = repository.save(mapper.toEntity(dto));
		return mapper.toResponseDto(saved);
	}

	@Override
	public List<RolePermissionResponseDto> assignBulk(RolePermissionBulkRequestDto dto) {
		List<RolePermission> entities = mapper.toEntityList(dto);
		List<RolePermission> saved = repository.saveAll(entities);
		return saved.stream()
				.map(mapper::toResponseDto)
				.toList();
	}

	@Override
	public List<RolePermissionResponseDto> getByRole(Long roleId) {
		return repository.findByRoleId(roleId)
				.stream()
				.map(mapper::toResponseDto)
				.toList();
	}

	@Override
	public List<RolePermissionResponseDto> getAll() {
		return repository.findAll()
				.stream()
				.map(mapper::toResponseDto)
				.toList();
	}

	@Override
	public void revoke(Long id) {
		repository.deleteById(id);
	}
}
