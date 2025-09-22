package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.OpdMedicalRecordRequestDTO;
import com.avsofthealthcare.dto.OpdMedicalRecordResponseDTO;
import com.avsofthealthcare.service.OpdMedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opd-records")
@RequiredArgsConstructor
public class OpdMedicalRecordController {

    private final OpdMedicalRecordService opdMedicalRecordService;

    @PostMapping
    public ResponseEntity<OpdMedicalRecordResponseDTO> createRecord(@RequestBody OpdMedicalRecordRequestDTO requestDTO) {
        return ResponseEntity.ok(opdMedicalRecordService.createRecord(requestDTO));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<OpdMedicalRecordResponseDTO>> getUserRecords(@PathVariable Long userId) {
        return ResponseEntity.ok(opdMedicalRecordService.getUserRecords(userId));
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long recordId) {
        opdMedicalRecordService.deleteRecord(recordId);
        return ResponseEntity.ok("OPD Medical Record deleted successfully");
    }
}
