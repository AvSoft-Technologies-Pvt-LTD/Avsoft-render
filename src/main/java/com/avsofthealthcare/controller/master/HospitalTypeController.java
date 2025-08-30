package com.avsofthealthcare.controller.master;
import com.avsofthealthcare.dto.master.HospitalTypeRequestDto;
import com.avsofthealthcare.dto.master.HospitalTypeResponseDto;
import com.avsofthealthcare.entity.master.HospitalType;
import com.avsofthealthcare.mapper.master.HospitalTypeMapper;
import com.avsofthealthcare.repository.master.HospitalTypeRepository;
import com.avsofthealthcare.service.master.HospitalTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/hospitalType")
@Tag(name = "Hospital Type", description = "CRUD for Hospital Type")
public class HospitalTypeController {

    @Autowired
    private HospitalTypeService hospitalTypeService;

    @Autowired
    private HospitalTypeRepository hospitalTypeRepository;


    @PostMapping
    public ResponseEntity<HospitalTypeResponseDto> createTest(@Valid @RequestBody HospitalTypeRequestDto dto) {
        HospitalType saved = hospitalTypeRepository.save(HospitalTypeMapper.toEntity(dto));
        return ResponseEntity.ok(HospitalTypeMapper.toDto(saved));
    }


    @GetMapping
    public List<HospitalTypeResponseDto> getAllTests() {
        return hospitalTypeService.findAllByIsDeletedFalse().stream()
                .map(HospitalTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalTypeResponseDto> getById(@PathVariable Integer id) {
        HospitalType bloodGroup = hospitalTypeService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(HospitalTypeMapper.toDto(bloodGroup));
    }


    @PutMapping("/{id}")
    public ResponseEntity<HospitalTypeResponseDto> update(
            @PathVariable Integer id,
            @Valid@RequestBody HospitalTypeRequestDto dto) {

        HospitalType updated = hospitalTypeService.update(id, dto);
        return ResponseEntity.ok(HospitalTypeMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        hospitalTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}


