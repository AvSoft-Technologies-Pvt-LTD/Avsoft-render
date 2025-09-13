package com.avsofthealthcare.controller.master;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.avsofthealthcare.dto.master.PermissionRequestDto;
import com.avsofthealthcare.dto.master.PermissionResponseDto;
import com.avsofthealthcare.service.master.PermissionService;


import java.util.List;

import lombok.RequiredArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/master/permissions")
@RequiredArgsConstructor
public class PermissionController {

	private final PermissionService permissionService;

	@PostMapping
	public ResponseEntity<PermissionResponseDto> create(@RequestBody PermissionRequestDto dto) {
		return ResponseEntity.ok(permissionService.create(dto));
	}

	@GetMapping
	public ResponseEntity<List<PermissionResponseDto>> getAll() {
		return ResponseEntity.ok(permissionService.getAll());
	}

	@PutMapping("/{id}")
	public ResponseEntity<PermissionResponseDto> update(@PathVariable Long id,
			@RequestBody PermissionRequestDto dto) {
		return ResponseEntity.ok(permissionService.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		permissionService.delete(id);
		return ResponseEntity.noContent().build();
	}

//	@GetMapping("/role/{roleId}")
//	public ResponseEntity<List<PermissionResponseDto>> getByRoleId(@PathVariable Long roleId) {
//		return ResponseEntity.ok(permissionService.getPermissionsByRoleId(roleId));
//	}
//
//	@GetMapping("/role/name/{roleName}")
//	public ResponseEntity<List<PermissionResponseDto>> getByRoleName(@PathVariable String roleName) {
//		return ResponseEntity.ok(permissionService.getPermissionsByRoleName(roleName));
//	}
}
