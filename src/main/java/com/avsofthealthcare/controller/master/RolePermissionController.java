package com.avsofthealthcare.controller.master;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avsofthealthcare.dto.master.RolePermissionDto;
import com.avsofthealthcare.service.master.RolePermissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/master/role-permissions")
@RequiredArgsConstructor
public class RolePermissionController {
	private final RolePermissionService service;

	@PostMapping
	public RolePermissionDto assign(@RequestBody RolePermissionDto dto) {
		return service.assign(dto);
	}

	@GetMapping("/role/{roleId}")
	public List<RolePermissionDto> getByRole(@PathVariable Long roleId) {
		return service.getByRole(roleId);
	}

	@DeleteMapping("/{id}")
	public void revoke(@PathVariable Long id) {
		service.revoke(id);
	}
}
