package com.avsofthealthcare.controller.dashboard.doctordashboard;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avsofthealthcare.dto.dashboard.doctordashboard.AssignPermissionsRequestDTO;
import com.avsofthealthcare.dto.dashboard.doctordashboard.UserPermissionResponseDTO;
import com.avsofthealthcare.service.dashboard.doctordashboard.UserPermissionService;

@RestController
@RequestMapping("/api/permissions")
public class UserPermissionController {

	private final UserPermissionService permissionService;

	public UserPermissionController(UserPermissionService permissionService) {
		this.permissionService = permissionService;
	}

	@GetMapping("/staff/{staffId}")
	public List<UserPermissionResponseDTO> getPermissions(@PathVariable Long staffId) {
		return permissionService.getPermissionsByStaffId(staffId);
	}

	@PostMapping("/assign")
	public ResponseEntity<String> assignPermissions(@RequestBody AssignPermissionsRequestDTO request) {
		permissionService.assignPermissions(request.getStaffId(), request.getPermissions());
		return ResponseEntity.ok("Permissions assigned successfully");
	}
}