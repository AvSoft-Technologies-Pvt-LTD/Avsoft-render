package com.avsofthealthcare.controller.dashboard.doctordashboard;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffPermissionBulkRequestDto;
import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffPermissionResponseDto;
import com.avsofthealthcare.service.dashboard.doctordashboard.StaffPermissionService;

import lombok.RequiredArgsConstructor;

import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffPermissionRequestDto;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff-permissions")
@RequiredArgsConstructor
public class StaffPermissionController {

	private final StaffPermissionService staffPermissionService;

	/**
	 * 🔹 Assign a single permission to staff
	 */
	@PostMapping("/{staffId}/assign")
	public ResponseEntity<StaffPermissionResponseDto> assignPermission(
			@PathVariable Long staffId,
			@RequestBody StaffPermissionRequestDto requestDto
	) {
		StaffPermissionResponseDto response = staffPermissionService.assignPermission(staffId, requestDto);
		return ResponseEntity.ok(response);
	}

	/**
	 * 🔹 Bulk assign permissions to staff
	 */
	@PostMapping("/{staffId}/bulk-assign")
	public ResponseEntity<List<StaffPermissionResponseDto>> bulkAssignPermissions(
			@PathVariable Long staffId,
			@RequestBody StaffPermissionBulkRequestDto bulkRequest
	) {
		List<StaffPermissionResponseDto> responses = staffPermissionService.bulkAssignPermissions(staffId, bulkRequest);
		return ResponseEntity.ok(responses);
	}

	/**
	 * 🔹 Get all permissions of a staff
	 */
	@GetMapping("/{staffId}")
	public ResponseEntity<List<StaffPermissionResponseDto>> getStaffPermissions(
			@PathVariable Long staffId
	) {
		List<StaffPermissionResponseDto> responses = staffPermissionService.getStaffPermissions(staffId);
		return ResponseEntity.ok(responses);
	}

	@DeleteMapping("/{staffPermissionId}")
	public ResponseEntity<Void> removePermission(@PathVariable Long staffPermissionId) {
		staffPermissionService.removePermission(staffPermissionId);
		return ResponseEntity.noContent().build(); // ✅ 204 No Content
	}

}
