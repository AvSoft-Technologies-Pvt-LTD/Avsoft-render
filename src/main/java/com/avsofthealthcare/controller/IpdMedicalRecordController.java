package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.IpdMedicalRecordRequestDTO;
import com.avsofthealthcare.dto.IpdMedicalRecordResponseDTO;
import com.avsofthealthcare.service.IpdMedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ipd-records")
@RequiredArgsConstructor
public class IpdMedicalRecordController {

    private final IpdMedicalRecordService ipdMedicalRecordService;

    @PostMapping
    public ResponseEntity<IpdMedicalRecordResponseDTO> createRecord(@RequestBody IpdMedicalRecordRequestDTO requestDTO) {
        return ResponseEntity.ok(ipdMedicalRecordService.createRecord(requestDTO));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<IpdMedicalRecordResponseDTO>> getUserRecords(@PathVariable Long userId) {
        return ResponseEntity.ok(ipdMedicalRecordService.getUserRecords(userId));
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long recordId) {
        ipdMedicalRecordService.deleteRecord(recordId);
        return ResponseEntity.ok("IPD Medical Record deleted successfully");
    }
}
