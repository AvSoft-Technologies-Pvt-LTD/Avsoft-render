package com.avsofthealthcare.controller.master;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.avsofthealthcare.dto.master.DesignationRequestDTO;
import com.avsofthealthcare.dto.master.DesignationResponseDTO;
import com.avsofthealthcare.service.master.DesignationService;

@RestController
@RequestMapping("/api/master/designations")
@RequiredArgsConstructor
public class DesignationController {

	private final DesignationService designationService;

	@PostMapping
	public ResponseEntity<DesignationResponseDTO> create(@RequestBody DesignationRequestDTO dto) {
		return ResponseEntity.ok(designationService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<DesignationResponseDTO> update(@PathVariable Long id,
			@RequestBody DesignationRequestDTO dto) {
		return ResponseEntity.ok(designationService.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		designationService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DesignationResponseDTO> getById(@PathVariable Long id) {
		return ResponseEntity.ok(designationService.getById(id));
	}

	@GetMapping
	public ResponseEntity<List<DesignationResponseDTO>> getAll() {
		return ResponseEntity.ok(designationService.getAll());
	}
}
