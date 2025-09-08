package com.avsofthealthcare.controller.dashboard.doctordashboard;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffRequestDTO;
import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffResponseDTO;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;
import com.avsofthealthcare.service.dashboard.doctordashboard.StaffService;

@RestController
@RequestMapping("/api/dashboard/staff")
@Tag(name = "Staff", description = "CRUD for Staff")
@RequiredArgsConstructor
public class StaffController {

	private final StaffService staffService;

	@PreAuthorize("@permissionChecker.hasPermission('staff','CREATE')")
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<StaffResponseDTO> create(@ModelAttribute StaffRequestDTO dto) {
		return ResponseEntity.ok(staffService.create(dto));
	}

	@PreAuthorize("@permissionChecker.hasPermission('staff','UPDATE')")
	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<StaffResponseDTO> update(@PathVariable Long id, @ModelAttribute StaffRequestDTO dto) {
		return ResponseEntity.ok(staffService.update(id, dto));
	}

	@PreAuthorize("@permissionChecker.hasPermission('staff','DELETE')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		staffService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("@permissionChecker.hasPermission('staff','VIEW')")
	@GetMapping("/{id}")
	public ResponseEntity<StaffResponseDTO> getById(@PathVariable Long id) {
		return ResponseEntity.ok(staffService.getById(id));
	}

	@PreAuthorize("@permissionChecker.hasPermission('staff','VIEW')")
	@GetMapping
	public ResponseEntity<List<StaffResponseDTO>> getAll() {
		return ResponseEntity.ok(staffService.getAll());
	}

	@GetMapping("/role/{roleId}")
	public List<StaffDetails> getStaffByRole(@PathVariable Long roleId) {
		return staffService.getStaffByRoleId(roleId);
	}

	@GetMapping("/role/name/{roleName}")
	public List<StaffDetails> getStaffByRoleName(@PathVariable String roleName) {
		return staffService.getStaffByRoleName(roleName);
	}
}
