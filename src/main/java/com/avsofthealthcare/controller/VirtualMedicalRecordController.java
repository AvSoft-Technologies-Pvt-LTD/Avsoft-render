package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.VirtualMedicalRecordRequestDTO;
import com.avsofthealthcare.dto.VirtualMedicalRecordResponseDTO;
import com.avsofthealthcare.service.VirtualMedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/virtual-records")
@RequiredArgsConstructor
public class VirtualMedicalRecordController {

    private final VirtualMedicalRecordService virtualMedicalRecordService;

    @PostMapping
    public ResponseEntity<VirtualMedicalRecordResponseDTO> createRecord(@RequestBody VirtualMedicalRecordRequestDTO requestDTO) {
        return ResponseEntity.ok(virtualMedicalRecordService.createRecord(requestDTO));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<VirtualMedicalRecordResponseDTO>> getUserRecords(@PathVariable Long userId) {
        return ResponseEntity.ok(virtualMedicalRecordService.getUserRecords(userId));
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long recordId) {
        virtualMedicalRecordService.deleteRecord(recordId);
        return ResponseEntity.ok("Virtual Medical Record deleted successfully");
    }
}
