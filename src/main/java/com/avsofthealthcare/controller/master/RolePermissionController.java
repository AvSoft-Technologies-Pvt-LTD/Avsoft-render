package com.avsofthealthcare.controller.master;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avsofthealthcare.dto.master.RolePermissionBulkRequestDto;
import com.avsofthealthcare.dto.master.RolePermissionRequestDto;
import com.avsofthealthcare.dto.master.RolePermissionResponseDto;
import com.avsofthealthcare.service.master.RolePermissionService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/master/role-permissions")
@RequiredArgsConstructor
public class RolePermissionController {

	private final RolePermissionService service;

	@PostMapping
	public RolePermissionResponseDto assign(@RequestBody RolePermissionRequestDto dto) {
		return service.assign(dto);
	}

	@PostMapping("/bulk")
	public List<RolePermissionResponseDto> assignBulk(@RequestBody RolePermissionBulkRequestDto dto) {
		return service.assignBulk(dto);
	}

	@GetMapping("/role/{roleId}")
	public List<RolePermissionResponseDto> getByRole(@PathVariable Long roleId) {
		return service.getByRole(roleId);
	}

	@GetMapping
	public List<RolePermissionResponseDto> getAll() {
		return service.getAll();
	}

	@DeleteMapping("/{id}")
	public void revoke(@PathVariable Long id) {
		service.revoke(id);
	}
}
