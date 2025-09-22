package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.SurgeryRequestDTO;
import com.avsofthealthcare.dto.SurgeryResponseDTO;
import com.avsofthealthcare.service.SurgeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users/{userId}/surgeries")
@RequiredArgsConstructor
public class SurgeryController {

    private final SurgeryService surgeryService;

    // CREATE
    @PostMapping
    public ResponseEntity<SurgeryResponseDTO> addSurgery(
            @PathVariable Long userId,
            @RequestBody SurgeryRequestDTO request) {
        return ResponseEntity.ok(surgeryService.addSurgery(userId, request));
    }

    // READ ALL by userId
    @GetMapping
    public ResponseEntity<List<SurgeryResponseDTO>> getSurgeries(@PathVariable Long userId) {
        return ResponseEntity.ok(surgeryService.getSurgeriesByUser(userId));
    }

    // READ SINGLE surgery
    @GetMapping("/{surgeryId}")
    public ResponseEntity<SurgeryResponseDTO> getSurgeryById(
            @PathVariable Long userId,
            @PathVariable Long surgeryId) {
        return ResponseEntity.ok(surgeryService.getSurgeryById(userId, surgeryId));
    }

    // UPDATE
    @PutMapping("/{surgeryId}")
    public ResponseEntity<SurgeryResponseDTO> updateSurgery(
            @PathVariable Long userId,
            @PathVariable Long surgeryId,
            @RequestBody SurgeryRequestDTO request) {
        return ResponseEntity.ok(surgeryService.updateSurgery(userId, surgeryId, request));
    }

    // DELETE
    @DeleteMapping("/{surgeryId}")
    public ResponseEntity<Map<String, String>> deleteSurgery(
            @PathVariable Long userId,
            @PathVariable Long surgeryId) {
        surgeryService.deleteSurgery(userId, surgeryId);
        return ResponseEntity.ok(Map.of("message", "Surgery deleted successfully"));
    }

}
