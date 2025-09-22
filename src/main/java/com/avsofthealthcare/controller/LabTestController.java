package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.LabTestRequestDTO;
import com.avsofthealthcare.dto.LabTestResponseDTO;
import com.avsofthealthcare.service.LabTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab/tests")
@RequiredArgsConstructor
public class LabTestController {

    private final LabTestService service;

    @GetMapping
    public ResponseEntity<List<LabTestResponseDTO>> getAllTests() {
        return ResponseEntity.ok(service.getAllTests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabTestResponseDTO> getTest(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTest(id));
    }

    @PostMapping
    public ResponseEntity<LabTestResponseDTO> addTest(@RequestBody LabTestRequestDTO request) {
        return ResponseEntity.ok(service.addTest(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabTestResponseDTO> updateTest(@PathVariable Long id,
                                                         @RequestBody LabTestRequestDTO request) {
        return ResponseEntity.ok(service.updateTest(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id) {
        service.deleteTest(id);
        return ResponseEntity.noContent().build();
    }
}
