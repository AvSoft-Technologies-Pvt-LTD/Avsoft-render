package com.avsofthealthcare.controller.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.AdditionalDetailsRequestDTO;
import com.avsofthealthcare.dto.dashboard.patientdashboard.AdditionalDetailsResponseDTO;
import com.avsofthealthcare.service.dashboard.patientdashboard.AdditionalDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/patient/additional-details")
@RequiredArgsConstructor
public class AdditionalDetailsController {

	private final AdditionalDetailsService additionalDetailsService;

	// CREATE
	@PostMapping("/{patientId}")
	public ResponseEntity<AdditionalDetailsResponseDTO> create(
			@PathVariable String patientId,
			@Valid @RequestBody AdditionalDetailsRequestDTO dto
	) {
		return ResponseEntity.ok(additionalDetailsService.create(patientId, dto));
	}

	// READ
	@GetMapping("/{patientId}")
	public ResponseEntity<AdditionalDetailsResponseDTO> getByPatientId(
			@PathVariable String patientId
	) {
		return ResponseEntity.ok(additionalDetailsService.findByPatientId(patientId));
	}

	// UPDATE
	@PutMapping("/{patientId}")
	public ResponseEntity<AdditionalDetailsResponseDTO> update(
			@PathVariable String patientId,
			@Valid @RequestBody AdditionalDetailsRequestDTO dto
	) {
		return ResponseEntity.ok(additionalDetailsService.updateByPatientId(patientId, dto));
	}

	// DELETE
	@DeleteMapping("/{patientId}")
	public ResponseEntity<String> delete(
			@PathVariable String patientId
	) {
		additionalDetailsService.delete(patientId);
		return ResponseEntity.ok("Additional details deleted successfully.");
	}
}
