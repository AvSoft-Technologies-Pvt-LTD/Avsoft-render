package com.avsofthealthcare.service.impl.dashboard.doctordashboard;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.avsofthealthcare.dto.dashboard.doctordashboard.UserPermissionRequestDTO;
import com.avsofthealthcare.dto.dashboard.doctordashboard.UserPermissionResponseDTO;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;
import com.avsofthealthcare.entity.dashboard.doctordashboard.UserPermission;
import com.avsofthealthcare.mapper.dashboard.doctordashboard.UserPermissionMapper;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffRepository;
import com.avsofthealthcare.repository.dashboard.doctordashboard.UserPermissionRepository;
import com.avsofthealthcare.service.dashboard.doctordashboard.UserPermissionService;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {

	private final UserPermissionRepository permissionRepository;
	private final StaffRepository staffRepository;


	public UserPermissionServiceImpl(UserPermissionRepository permissionRepository,
			StaffRepository staffRepository) {
		this.permissionRepository = permissionRepository;
		this.staffRepository = staffRepository;
	}


	@Override
	public List<UserPermissionResponseDTO> getPermissionsByStaffId(Long staffId) {
		List<UserPermission> permissions = permissionRepository.findByStaffId(staffId);
		return permissions.stream()
				.map(UserPermissionMapper::toResponseDTO)
				.collect(Collectors.toList());
	}

	@Override
	public void assignPermissions(Long staffId, List<UserPermissionRequestDTO> permissionDtos) {
		StaffDetails staff = staffRepository.findById(staffId)
				.orElseThrow(() -> new RuntimeException("Staff not found"));

		// Optionally clear existing permissions
		permissionRepository.deleteAll(permissionRepository.findByStaffId(staffId));

		List<UserPermission> permissions = permissionDtos.stream()
				.map(dto -> {
					UserPermission entity = UserPermissionMapper.toEntity(dto, staff);
					entity.setStaff(staff);
					return entity;
				})
				.collect(Collectors.toList());

		permissionRepository.saveAll(permissions);
	}
}
