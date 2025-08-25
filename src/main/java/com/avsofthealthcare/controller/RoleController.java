package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.*;
import com.avsofthealthcare.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

	private final RoleService roleService;

	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping
	public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto roleRequestDto) {
		return ResponseEntity.ok(roleService.createRole(roleRequestDto));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<RoleResponseDto> updateRole(@PathVariable Long id,
			@RequestBody RoleUpdateDto roleUpdateDto) {
		return ResponseEntity.ok(roleService.updateRole(id, roleUpdateDto));
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
		return ResponseEntity.ok("Role deleted successfully");
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public ResponseEntity<List<RoleResponseDto>> getAllRoles() {
		return ResponseEntity.ok(roleService.getAllRoles());
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/{id}")
	public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable Long id) {
		return ResponseEntity.ok(roleService.getRoleById(id));
	}
}

