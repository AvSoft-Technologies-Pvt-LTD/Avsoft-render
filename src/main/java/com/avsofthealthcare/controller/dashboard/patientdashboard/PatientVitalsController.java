package com.avsofthealthcare.controller.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.PatientVitalsRequestDTO;
import com.avsofthealthcare.dto.dashboard.patientdashboard.PatientVitalsResponseDTO;
import com.avsofthealthcare.service.dashboard.patientdashboard.PatientVitalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient/vitals")
@RequiredArgsConstructor
public class PatientVitalsController {

	private final PatientVitalsService patientVitalsService;

	@PostMapping
	public ResponseEntity<PatientVitalsResponseDTO> create(@RequestBody PatientVitalsRequestDTO dto) {
		return ResponseEntity.ok(patientVitalsService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PatientVitalsResponseDTO> update(@PathVariable Long id,
			@RequestBody PatientVitalsRequestDTO dto) {
		return ResponseEntity.ok(patientVitalsService.update(id, dto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PatientVitalsResponseDTO> getById(@PathVariable Long id) {
		return ResponseEntity.ok(patientVitalsService.getById(id));
	}

	@GetMapping
	public ResponseEntity<List<PatientVitalsResponseDTO>> getAll() {
		return ResponseEntity.ok(patientVitalsService.getAll());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		patientVitalsService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
