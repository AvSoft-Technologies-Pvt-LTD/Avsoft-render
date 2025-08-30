package com.avsofthealthcare.controller.master;

import com.avsofthealthcare.dto.master.MedicalStatusRequestDto;
import com.avsofthealthcare.dto.master.MedicalStatusResponseDto;
import com.avsofthealthcare.entity.master.MedicalStatus;
import com.avsofthealthcare.mapper.master.MedicalStatusMapper;
import com.avsofthealthcare.repository.master.MedicalStatusRepository;
import com.avsofthealthcare.service.master.MedicalStatusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/medicalStatus")
@Tag(name = "Medical Status", description = "CRUD for Medical Status")
public class MedicalStatusController {

    @Autowired
    private MedicalStatusService medicalStatusService;

    @Autowired
    private MedicalStatusRepository medicalStatusRepository;


    @PostMapping
    public ResponseEntity<MedicalStatusResponseDto> createTest(@Valid @RequestBody MedicalStatusRequestDto dto) {
        MedicalStatus saved = medicalStatusRepository.save(MedicalStatusMapper.toEntity(dto));
        return ResponseEntity.ok(MedicalStatusMapper.toDto(saved));
    }


    @GetMapping
    public List<MedicalStatusResponseDto> getAllTests() {
        return medicalStatusService.findAllByIsDeletedFalse().stream()
                .map(MedicalStatusMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalStatusResponseDto> getById(@PathVariable Integer id) {
        MedicalStatus bloodGroup = medicalStatusService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(MedicalStatusMapper.toDto(bloodGroup));
    }


    @PutMapping("/{id}")
    public ResponseEntity<MedicalStatusResponseDto> update(
            @PathVariable Integer id,
            @Valid@RequestBody MedicalStatusRequestDto dto) {

        MedicalStatus updated = medicalStatusService.update(id, dto);
        return ResponseEntity.ok(MedicalStatusMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        medicalStatusService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

