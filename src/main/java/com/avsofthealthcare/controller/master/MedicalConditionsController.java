package com.avsofthealthcare.controller.master;


import com.avsofthealthcare.dto.master.MedicalConditionsRequestDto;
import com.avsofthealthcare.dto.master.MedicalConditionsResponseDto;
import com.avsofthealthcare.entity.master.MedicalConditions;
import com.avsofthealthcare.mapper.master.MedicalConditionsMapper;
import com.avsofthealthcare.repository.master.MedicalConditionsRepository;
import com.avsofthealthcare.service.master.MedicalConditionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/medicalConditions")
@Tag(name = "Medical Conditions", description = "CRUD for Medical Conditions")
public class MedicalConditionsController {

    @Autowired
    private MedicalConditionsService medicalConditionsService;

    @Autowired
    private MedicalConditionsRepository medicalConditionsRepository;


    @PostMapping
    public ResponseEntity<MedicalConditionsResponseDto> createTest(@Valid @RequestBody MedicalConditionsRequestDto dto) {
        MedicalConditions saved = medicalConditionsRepository.save(MedicalConditionsMapper.toEntity(dto));
        return ResponseEntity.ok(MedicalConditionsMapper.toDto(saved));
    }


    @GetMapping
    public List<MedicalConditionsResponseDto> getAllTests() {
        return medicalConditionsService.findAllByIsDeletedFalse().stream()
                .map(MedicalConditionsMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalConditionsResponseDto> getById(@PathVariable Integer id) {
        MedicalConditions bloodGroup = medicalConditionsService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(MedicalConditionsMapper.toDto(bloodGroup));
    }


    @PutMapping("/{id}")
    public ResponseEntity<MedicalConditionsResponseDto> update(
            @PathVariable Integer id,
            @Valid@RequestBody MedicalConditionsRequestDto dto) {

        MedicalConditions updated = medicalConditionsService.update(id, dto);
        return ResponseEntity.ok(MedicalConditionsMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        medicalConditionsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
