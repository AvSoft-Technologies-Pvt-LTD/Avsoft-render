package com.avsofthealthcare.service.impl.master;

import java.util.List;

import org.springframework.stereotype.Service;

import com.avsofthealthcare.dto.master.UserPermissionDto;
import com.avsofthealthcare.mapper.master.UserPermissionMapper;
import com.avsofthealthcare.repository.master.UserPermissionRepository;
import com.avsofthealthcare.service.master.UserPermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPermissionServiceImpl implements UserPermissionService {
	private final UserPermissionRepository repository;
	private final UserPermissionMapper mapper;

	@Override
	public UserPermissionDto assign(UserPermissionDto dto) {
		return mapper.toDto(repository.save(mapper.toEntity(dto)));
	}

	@Override
	public List<UserPermissionDto> getByStaff(Long staffId) {
		return repository.findByStaffId(staffId).stream().map(mapper::toDto).toList();
	}

	@Override
	public void revoke(Long id) {
		repository.deleteById(id);
	}
}