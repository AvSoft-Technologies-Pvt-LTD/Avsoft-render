package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.MedicalRecordRequestDTO;
import com.avsofthealthcare.dto.MedicalRecordResponseDTO;
import com.avsofthealthcare.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    // Create a new record
    @PostMapping
    public MedicalRecordResponseDTO createRecord(@RequestBody MedicalRecordRequestDTO dto) {
        return medicalRecordService.createRecord(dto);
    }

    // Get all records for a user (optionally by type)
    @GetMapping("/user/{userId}")
    public List<MedicalRecordResponseDTO> getUserRecords(
            @PathVariable Long userId,
            @RequestParam(required = false) String type) {
        return medicalRecordService.getUserRecords(userId, type);
    }

    // Delete a record by ID
    @DeleteMapping("/{id}")
    public String deleteRecord(@PathVariable Long id) {
        medicalRecordService.deleteRecord(id);
        return "Record deleted successfully";
    }
}
