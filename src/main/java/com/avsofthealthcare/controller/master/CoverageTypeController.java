package com.avsofthealthcare.controller.master;


import com.avsofthealthcare.dto.master.CoverageTypeRequestDto;
import com.avsofthealthcare.dto.master.CoverageTypeResponseDto;
import com.avsofthealthcare.entity.master.CoverageType;
import com.avsofthealthcare.mapper.master.CoverageTypeMapper;
import com.avsofthealthcare.repository.master.CoverageTypeRepository;
import com.avsofthealthcare.service.master.CoverageTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/coverage-type")
@Tag(name = "Coverage Type", description = "CRUD for Coverage Type")
public class CoverageTypeController {

    @Autowired
    private CoverageTypeService coverageTypeService;

    @Autowired
    private CoverageTypeRepository coverageTypeRepository;


    @PostMapping
    public ResponseEntity<CoverageTypeResponseDto> createTest(@Valid @RequestBody CoverageTypeRequestDto dto) {
        CoverageType saved = coverageTypeRepository.save(CoverageTypeMapper.toEntity(dto));
        return ResponseEntity.ok(CoverageTypeMapper.toDto(saved));
    }


    @GetMapping
    public List<CoverageTypeResponseDto> getAllTests() {
        return coverageTypeService.findAllByIsDeletedFalse().stream()
                .map(CoverageTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoverageTypeResponseDto> getById(@PathVariable Integer id) {
        CoverageType coverageType = coverageTypeService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(CoverageTypeMapper.toDto(coverageType));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CoverageTypeResponseDto> update(
            @PathVariable Integer id,
            @Valid@RequestBody CoverageTypeRequestDto dto) {

        CoverageType updated = coverageTypeService.update(id, dto);
        return ResponseEntity.ok(CoverageTypeMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        coverageTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

