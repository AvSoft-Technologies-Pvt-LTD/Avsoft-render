package com.avsofthealthcare.controller;

import com.avsofthealthcare.entity.VitalDetails;
import com.avsofthealthcare.service.VitalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vitals")
public class VitalDetailsController {

    @Autowired
    private VitalDetailsService vitalDetailsService;

    // Create
    @PostMapping("/add")
    public ResponseEntity<VitalDetails> addVital(@RequestBody VitalDetails vitalDetails) {
        return ResponseEntity.ok(vitalDetailsService.saveVital(vitalDetails));
    }

    // Read all
    @GetMapping("/all")
    public ResponseEntity<List<VitalDetails>> getAllVitals() {
        return ResponseEntity.ok(vitalDetailsService.getAllVitals());
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<VitalDetails> getVitalById(@PathVariable Long id) {
        return ResponseEntity.ok(vitalDetailsService.getVitalById(id));
    }

    // Update
    @PutMapping("/update/{id}")
    public ResponseEntity<VitalDetails> updateVital(@PathVariable Long id, @RequestBody VitalDetails vitalDetails) {
        return ResponseEntity.ok(vitalDetailsService.updateVital(id, vitalDetails));
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVital(@PathVariable Long id) {
        vitalDetailsService.deleteVital(id);
        return ResponseEntity.ok("Vital record deleted successfully.");
    }
}
