package com.avsofthealthcare.controller.master;

import com.avsofthealthcare.dto.master.PracticeTypeRequestDto;
import com.avsofthealthcare.dto.master.PracticeTypeResponseDto;
import com.avsofthealthcare.entity.master.PracticeType;
import com.avsofthealthcare.mapper.master.PracticeTypeMapper;
import com.avsofthealthcare.repository.master.PracticeTypeRepository;
import com.avsofthealthcare.service.master.PracticeTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/practiceType")
@Tag(name = "Practice Type", description = "CRUD for Practice Type")
public class PracticeTypeController {

    @Autowired
    private PracticeTypeService practiceTypeService;

    @Autowired
    private PracticeTypeRepository practiceTypeRepository;


    @PostMapping
    public ResponseEntity<PracticeTypeResponseDto> createTest(@Valid @RequestBody PracticeTypeRequestDto dto) {
        PracticeType saved = practiceTypeRepository.save(PracticeTypeMapper.toEntity(dto));
        return ResponseEntity.ok(PracticeTypeMapper.toDto(saved));
    }


    @GetMapping
    public List<PracticeTypeResponseDto> getAllTests() {
        return practiceTypeService.findAllByIsDeletedFalse().stream()
                .map(PracticeTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PracticeTypeResponseDto> getById(@PathVariable Integer id) {
        PracticeType bloodGroup = practiceTypeService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(PracticeTypeMapper.toDto(bloodGroup));
    }


    @PutMapping("/{id}")
    public ResponseEntity<PracticeTypeResponseDto> update(
            @PathVariable Integer id,
            @Valid@RequestBody PracticeTypeRequestDto dto) {

        PracticeType updated = practiceTypeService.update(id, dto);
        return ResponseEntity.ok(PracticeTypeMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        practiceTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}


