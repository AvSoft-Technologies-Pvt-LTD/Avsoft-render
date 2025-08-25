package com.avsofthealthcare.controller.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.PersonalHealthDetailsRequestDto;
import com.avsofthealthcare.dto.dashboard.patientdashboard.PersonalHealthDetailsResponseDto;
import com.avsofthealthcare.service.dashboard.patientdashboard.PersonalHealthDetailsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patient-dashboard/personal-health")
@Tag(name = "Personal Health Details", description = "CRUD for Patient's Personal Health Details")
public class PersonalHealthDetailsController {

    @Autowired
    private PersonalHealthDetailsService personalHealthDetailsService;

    @PostMapping
    public ResponseEntity<PersonalHealthDetailsResponseDto> create(@RequestBody PersonalHealthDetailsRequestDto dto) {
        return ResponseEntity.ok(personalHealthDetailsService.create(dto));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<PersonalHealthDetailsResponseDto> getByPatientId(@PathVariable String patientId) {
        return ResponseEntity.ok(personalHealthDetailsService.findByPatientId(patientId));
    }

//    @GetMapping
//    public List<PersonalHealthDetailsResponseDto> getAll(@RequestParam String patientId) {
//        return personalHealthDetailsService.findAllByPatientId(patientId);
//    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PersonalHealthDetailsResponseDto> update(
            @PathVariable String patientId,
            @RequestBody PersonalHealthDetailsRequestDto dto) {
        return ResponseEntity.ok(personalHealthDetailsService.updateByPatientId(patientId, dto));
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> delete(@PathVariable String patientId) {
        personalHealthDetailsService.delete(patientId);
        return ResponseEntity.noContent().build();
    }
}