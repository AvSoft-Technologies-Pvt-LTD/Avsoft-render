package com.avsofthealthcare.controller.master;


import com.avsofthealthcare.dto.master.HealthConditionsRequestDto;
import com.avsofthealthcare.dto.master.HealthConditionsResponseDto;
import com.avsofthealthcare.entity.master.HealthConditions;
import com.avsofthealthcare.mapper.master.HealthConditionsMapper;
import com.avsofthealthcare.repository.master.HealthConditionsRepository;
import com.avsofthealthcare.service.master.HealthConditionsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/healthConditions")
@Tag(name = "Health Conditions", description = "CRUD for Health Conditions")
public class HealthConditionsController {

    @Autowired
    private HealthConditionsService healthConditionsService;

    @Autowired
    private HealthConditionsRepository healthConditionsRepository;


    @PostMapping
    public ResponseEntity<HealthConditionsResponseDto> createTest(@Valid @RequestBody HealthConditionsRequestDto dto) {
        HealthConditions saved = healthConditionsRepository.save(HealthConditionsMapper.toEntity(dto));
        return ResponseEntity.ok(HealthConditionsMapper.toDto(saved));
    }


    @GetMapping
    public List<HealthConditionsResponseDto> getAllTests() {
        return healthConditionsService.findAllByIsDeletedFalse().stream()
                .map(HealthConditionsMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthConditionsResponseDto> getById(@PathVariable Integer id) {
        HealthConditions bloodGroup = healthConditionsService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(HealthConditionsMapper.toDto(bloodGroup));
    }


    @PutMapping("/{id}")
    public ResponseEntity<HealthConditionsResponseDto> update(
            @PathVariable Integer id,
            @Valid@RequestBody HealthConditionsRequestDto dto) {

        HealthConditions updated = healthConditionsService.update(id, dto);
        return ResponseEntity.ok(HealthConditionsMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        healthConditionsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}


