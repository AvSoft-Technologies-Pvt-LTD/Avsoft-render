package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.VitalDetailsRequestDTO;
import com.avsofthealthcare.dto.VitalDetailsResponseDTO;
import com.avsofthealthcare.entity.PatientDetails;
import com.avsofthealthcare.entity.VitalDetails;
import com.avsofthealthcare.mapper.VitalDetailsMapper;
import com.avsofthealthcare.repository.PatientDetailsRepository;
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

    @Autowired
    private PatientDetailsRepository patientDetailsRepository; // ✅ inject repository

    // Create
    @PostMapping("/add")
    public ResponseEntity<VitalDetailsResponseDTO> addVital(@RequestBody VitalDetailsRequestDTO vitalDetailsDto) {
        PatientDetails patient = patientDetailsRepository.findById(vitalDetailsDto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + vitalDetailsDto.getPatientId()));

        VitalDetails vitalDetails = new VitalDetails();
        vitalDetails.setHeartRate(vitalDetailsDto.getHeartRate());
        vitalDetails.setTemperature(vitalDetailsDto.getTemperature());
        vitalDetails.setBloodSugar(vitalDetailsDto.getBloodSugar());
        vitalDetails.setBloodPressure(vitalDetailsDto.getBloodPressure());
        vitalDetails.setRespiratoryRate(vitalDetailsDto.getRespiratoryRate());
        vitalDetails.setSpO2(vitalDetailsDto.getSpO2());
        vitalDetails.setSteps(vitalDetailsDto.getSteps());
        vitalDetails.setPatient(patient); // ✅ only link by patientId

        VitalDetails saved = vitalDetailsService.saveVital(vitalDetails);
        return ResponseEntity.ok(VitalDetailsMapper.toResponseDTO(saved));
    }


    // Read all
    @GetMapping("/all")
    public ResponseEntity<List<VitalDetailsResponseDTO>> getAllVitals() {
        List<VitalDetails> vitals = vitalDetailsService.getAllVitals();
        List<VitalDetailsResponseDTO> response = vitals.stream()
                .map(VitalDetailsMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VitalDetailsResponseDTO> getVitalById(@PathVariable Long id) {
        VitalDetails vital = vitalDetailsService.getVitalById(id);
        return ResponseEntity.ok(VitalDetailsMapper.toResponseDTO(vital));
    }


    // Update
    @PutMapping("/update/{id}")
    public ResponseEntity<VitalDetailsResponseDTO> updateVital(
            @PathVariable Long id,
            @RequestBody VitalDetailsRequestDTO vitalDetailsDto) {

        VitalDetails existingVital = vitalDetailsService.getVitalById(id);

        PatientDetails patient = patientDetailsRepository.findById(vitalDetailsDto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + vitalDetailsDto.getPatientId()));

        existingVital.setHeartRate(vitalDetailsDto.getHeartRate());
        existingVital.setTemperature(vitalDetailsDto.getTemperature());
        existingVital.setBloodSugar(vitalDetailsDto.getBloodSugar());
        existingVital.setBloodPressure(vitalDetailsDto.getBloodPressure());
        existingVital.setRespiratoryRate(vitalDetailsDto.getRespiratoryRate());
        existingVital.setSpO2(vitalDetailsDto.getSpO2());
        existingVital.setSteps(vitalDetailsDto.getSteps());
        existingVital.setPatient(patient); // ✅ re-link patient

        VitalDetails updated = vitalDetailsService.saveVital(existingVital);
        return ResponseEntity.ok(VitalDetailsMapper.toResponseDTO(updated));
    }


    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVital(@PathVariable Long id) {
        vitalDetailsService.deleteVital(id);
        return ResponseEntity.ok("Vital record deleted successfully.");
    }
}
