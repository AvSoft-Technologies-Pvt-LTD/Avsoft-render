package com.avsofthealthcare.controller.master;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.avsofthealthcare.dto.master.PermissionDto;
import com.avsofthealthcare.service.master.PermissionService;


import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/master/permissions")
@RequiredArgsConstructor
public class PermissionController {

	private final PermissionService permissionService;

	@PostMapping
	public ResponseEntity<PermissionDto> create(@RequestBody PermissionDto dto) {
		return ResponseEntity.ok(permissionService.create(dto));
	}

	@GetMapping
	public ResponseEntity<List<PermissionDto>> getAll() {
		return ResponseEntity.ok(permissionService.getAll());
	}

	@PutMapping("/{id}")
	public ResponseEntity<PermissionDto> update(@PathVariable Long id, @RequestBody PermissionDto dto) {
		return ResponseEntity.ok(permissionService.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		permissionService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// ✅ Get permissions by Role ID
	@GetMapping("/role/{roleId}")
	public ResponseEntity<List<PermissionDto>> getByRoleId(@PathVariable Long roleId) {
		return ResponseEntity.ok(permissionService.getPermissionsByRoleId(roleId));
	}

	// ✅ Get permissions by Role Name
	@GetMapping("/role/name/{roleName}")
	public ResponseEntity<List<PermissionDto>> getByRoleName(@PathVariable String roleName) {
		return ResponseEntity.ok(permissionService.getPermissionsByRoleName(roleName));
	}
}