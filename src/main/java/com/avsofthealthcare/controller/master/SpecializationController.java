package com.avsofthealthcare.controller.master;

import com.avsofthealthcare.dto.master.SpecializationRequestDto;
import com.avsofthealthcare.dto.master.SpecializationResponseDto;
import com.avsofthealthcare.entity.master.Specialization;
import com.avsofthealthcare.mapper.master.SpecializationMapper;
import com.avsofthealthcare.repository.master.PracticeTypeRepository;
import com.avsofthealthcare.service.master.SpecializationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/specializations")
@Tag(name = "Specialization", description = "CRUD for Specializations")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private PracticeTypeRepository practiceTypeRepository;

    @PostMapping
    public ResponseEntity<SpecializationResponseDto> create(@RequestBody SpecializationRequestDto dto) {
        Specialization saved = specializationService.create(dto);
        return ResponseEntity.ok(SpecializationMapper.toDto(saved));
    }

    @GetMapping
    public List<SpecializationResponseDto> getAll() {
        return specializationService.findAllByIsDeletedFalse().stream()
                .map(SpecializationMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecializationResponseDto> getById(@PathVariable Integer id) {
        Specialization specialization = specializationService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(SpecializationMapper.toDto(specialization));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecializationResponseDto> update(
            @PathVariable Integer id,
            @RequestBody SpecializationRequestDto dto) {

        Specialization updated = specializationService.update(id, dto);
        return ResponseEntity.ok(SpecializationMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        specializationService.delete(id);
        return ResponseEntity.noContent().build();
    }

	@GetMapping("/by-practice-type")
	public ResponseEntity<List<SpecializationResponseDto>> getByPracticeTypeId(
			@RequestParam("practiceTypeId") Integer practiceTypeId) {

		List<SpecializationResponseDto> specializations = specializationService.getSpecializationsByPracticeTypeId(practiceTypeId);
		return ResponseEntity.ok(specializations);
	}


}