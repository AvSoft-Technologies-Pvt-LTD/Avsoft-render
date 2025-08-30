package com.avsofthealthcare.controller.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.FamilyMemberRequestDTO;
import com.avsofthealthcare.dto.dashboard.patientdashboard.FamilyMemberResponseDTO;
import com.avsofthealthcare.service.dashboard.patientdashboard.FamilyMemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient-dashboard/family-members")
@Tag(name = "Family Member Health Conditions", description = "CRUD for Family Member Health Conditions")
@RequiredArgsConstructor
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;

    /**
     * 🔹 Create a new family member
     */
    @PostMapping
    public ResponseEntity<FamilyMemberResponseDTO> create(@Valid @RequestBody FamilyMemberRequestDTO requestDTO) {
        FamilyMemberResponseDTO created = familyMemberService.create(requestDTO);
        return ResponseEntity.ok(created);
    }

    /**
     * 🔹 Get all members by patient ID
     */
    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<FamilyMemberResponseDTO>> getByPatientId(@PathVariable String patientId) {
        List<FamilyMemberResponseDTO> list = familyMemberService.getByPatientId(patientId);
        return ResponseEntity.ok(list);
    }

    /**
     * 🔹 Get a single member by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<FamilyMemberResponseDTO> getById(@PathVariable Long id) {
        FamilyMemberResponseDTO member = familyMemberService.getById(id);
        return ResponseEntity.ok(member);
    }

    /**
     * 🔹 Update family member
     */
    @PutMapping("/{id}")
    public ResponseEntity<FamilyMemberResponseDTO> update(@PathVariable Long id,
                                                          @Valid@RequestBody FamilyMemberRequestDTO requestDTO) {
        FamilyMemberResponseDTO updated = familyMemberService.update(id, requestDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * 🔹 Delete family member
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        familyMemberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
