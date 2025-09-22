package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.LabScanRequestDTO;
import com.avsofthealthcare.dto.LabScanResponseDTO;
import com.avsofthealthcare.service.LabScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab/scans")
@RequiredArgsConstructor
public class LabScanController {

    private final LabScanService service;

    @GetMapping
    public ResponseEntity<List<LabScanResponseDTO>> getAllScans() {
        return ResponseEntity.ok(service.getAllScans());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabScanResponseDTO> getScan(@PathVariable Long id) {
        return ResponseEntity.ok(service.getScan(id));
    }

    @PostMapping
    public ResponseEntity<LabScanResponseDTO> addScan(@RequestBody LabScanRequestDTO request) {
        return ResponseEntity.ok(service.addScan(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabScanResponseDTO> updateScan(@PathVariable Long id,
                                                         @RequestBody LabScanRequestDTO request) {
        return ResponseEntity.ok(service.updateScan(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScan(@PathVariable Long id) {
        service.deleteScan(id);
        return ResponseEntity.noContent().build();
    }
}
