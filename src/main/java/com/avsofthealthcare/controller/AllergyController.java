package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.AllergyRequestDTO;
import com.avsofthealthcare.dto.AllergyResponseDTO;
import com.avsofthealthcare.service.AllergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/allergies")
@RequiredArgsConstructor
public class AllergyController {

    private final AllergyService allergyService;

    // CREATE
    @PostMapping
    public ResponseEntity<AllergyResponseDTO> addAllergy(
            @PathVariable Long userId,
            @RequestBody AllergyRequestDTO request) {
        return ResponseEntity.ok(allergyService.addAllergy(userId, request));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<AllergyResponseDTO>> getAllergies(@PathVariable Long userId) {
        return ResponseEntity.ok(allergyService.getAllergiesByUser(userId));
    }

    // READ SINGLE
    @GetMapping("/{allergyId}")
    public ResponseEntity<AllergyResponseDTO> getAllergyById(
            @PathVariable Long userId,
            @PathVariable Long allergyId) {
        return ResponseEntity.ok(allergyService.getAllergyById(userId, allergyId));
    }

    // UPDATE
    @PutMapping("/{allergyId}")
    public ResponseEntity<AllergyResponseDTO> updateAllergy(
            @PathVariable Long userId,
            @PathVariable Long allergyId,
            @RequestBody AllergyRequestDTO request) {
        return ResponseEntity.ok(allergyService.updateAllergy(userId, allergyId, request));
    }

    // DELETE
    @DeleteMapping("/{allergyId}")
    public ResponseEntity<String> deleteAllergy(
            @PathVariable Long userId,
            @PathVariable Long allergyId) {
        allergyService.deleteAllergy(userId, allergyId);
        return ResponseEntity.ok("Allergy deleted successfully");
    }

}
