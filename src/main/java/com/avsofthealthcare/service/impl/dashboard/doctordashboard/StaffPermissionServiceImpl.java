package com.avsofthealthcare.service.impl.dashboard.doctordashboard;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffPermissionBulkRequestDto;
import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffPermissionRequestDto;
import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffPermissionResponseDto;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffPermission;
import com.avsofthealthcare.entity.master.Permission;
import com.avsofthealthcare.mapper.dashboard.doctordashboard.StaffPermissionMapper;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffPermissionRepository;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffDetailsRepository;
import com.avsofthealthcare.repository.master.PermissionRepository;
import com.avsofthealthcare.service.dashboard.doctordashboard.StaffPermissionService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffPermissionServiceImpl implements StaffPermissionService {

	private final StaffPermissionRepository staffPermissionRepository;
	private final StaffDetailsRepository staffDetailsRepository;
	private final PermissionRepository permissionRepository;
	private final StaffPermissionMapper mapper;

	@Override
	@Transactional
	public StaffPermissionResponseDto assignPermission(Long staffId, StaffPermissionRequestDto requestDto) {
		StaffDetails staff = staffDetailsRepository.findById(staffId)
				.orElseThrow(() -> new EntityNotFoundException("Staff not found with ID: " + staffId));

		Permission permission = permissionRepository.findById(requestDto.getPermissionId())
				.orElseThrow(() -> new EntityNotFoundException("Permission not found with ID: " + requestDto.getPermissionId()));

		boolean exists = staffPermissionRepository.existsByStaffAndPermission(staff, permission);
		if (exists) {
			throw new IllegalStateException("Staff already has this permission assigned");
		}

		StaffPermission staffPermission = mapper.toEntity(requestDto, staff, permission);
		staffPermissionRepository.save(staffPermission);

		return mapper.toResponseDto(staffPermission);
	}

	@Override
	@Transactional
	public List<StaffPermissionResponseDto> bulkAssignPermissions(Long staffId, StaffPermissionBulkRequestDto bulkRequest) {
		StaffDetails staff = staffDetailsRepository.findById(staffId)
				.orElseThrow(() -> new EntityNotFoundException("Staff not found with ID: " + staffId));

		List<Permission> permissions = permissionRepository.findAllById(bulkRequest.getPermissionIds());

		List<StaffPermission> staffPermissions = permissions.stream()
				.filter(permission -> !staffPermissionRepository.existsByStaffAndPermission(staff, permission))
				.map(permission -> mapper.toEntity(staff, permission))
				.collect(Collectors.toList());

		staffPermissionRepository.saveAll(staffPermissions);

		return staffPermissions.stream()
				.map(mapper::toResponseDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<StaffPermissionResponseDto> getStaffPermissions(Long staffId) {
		StaffDetails staff = staffDetailsRepository.findById(staffId)
				.orElseThrow(() -> new EntityNotFoundException("Staff not found with ID: " + staffId));

		return staffPermissionRepository.findByStaff(staff).stream()
				.map(mapper::toResponseDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void removePermission(Long staffPermissionId) {
		StaffPermission staffPermission = staffPermissionRepository.findById(staffPermissionId)
				.orElseThrow(() -> new EntityNotFoundException("StaffPermission not found with ID: " + staffPermissionId));

		staffPermissionRepository.delete(staffPermission);
	}

}
