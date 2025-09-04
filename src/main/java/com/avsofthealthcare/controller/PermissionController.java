package com.avsofthealthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.avsofthealthcare.dto.PermissionRequestDTO;
import com.avsofthealthcare.dto.PermissionResponseDTO;
import com.avsofthealthcare.entity.Permission;
import com.avsofthealthcare.service.PermissionService;
import com.avsofthealthcare.mapper.PermissionMapper;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@PreAuthorize("hasRole('ADMIN')") // only admin can create
	@PostMapping
	public ResponseEntity<PermissionResponseDTO> createPermission(@Valid @RequestBody PermissionRequestDTO dto) {
		Permission permissionEntity = PermissionMapper.toEntity(dto);
		Permission savedPermission = permissionService.savePermission(permissionEntity);
		return ResponseEntity.ok(PermissionMapper.toResponseDTO(savedPermission));
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')") // admin or manager
	public ResponseEntity<PermissionResponseDTO> updatePermission(@PathVariable Long id, @Valid @RequestBody PermissionRequestDTO dto) {
		Permission permissionEntity = PermissionMapper.toEntity(dto);
		permissionEntity.setId(id);
		Permission updatedPermission = permissionService.updatePermission(permissionEntity);
		return ResponseEntity.ok(PermissionMapper.toResponseDTO(updatedPermission));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
		permissionService.deletePermission(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("/{id}")
	public ResponseEntity<PermissionResponseDTO> getPermission(@PathVariable Long id) {
		Permission permission = permissionService.getPermissionById(id);
		return ResponseEntity.ok(PermissionMapper.toResponseDTO(permission));
	}

	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
	@GetMapping
	public ResponseEntity<List<PermissionResponseDTO>> getAllPermissions() {
		List<Permission> permissions = permissionService.getAllPermissions();
		List<PermissionResponseDTO> dtos = permissions.stream()
				.map(PermissionMapper::toResponseDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
}