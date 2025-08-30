package com.avsofthealthcare.controller.master;

import com.avsofthealthcare.dto.master.BloodGroupRequestDto;
import com.avsofthealthcare.dto.master.BloodGroupResponseDto;
import com.avsofthealthcare.entity.master.BloodGroup;
import com.avsofthealthcare.mapper.master.BloodGroupMapper;
import com.avsofthealthcare.repository.master.BloodGroupRepository;
import com.avsofthealthcare.service.master.BloodGroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/blood-group")
@Tag(name = "Blood Group", description = "CRUD for Blood Group")
public class BloodGroupController {

    @Autowired
    private BloodGroupService bloodGroupService;

    @Autowired
    private BloodGroupRepository bloodGroupRepository;


    @PostMapping
    public ResponseEntity<BloodGroupResponseDto> createTest(@Valid @RequestBody BloodGroupRequestDto dto) {
        BloodGroup saved = bloodGroupRepository.save(BloodGroupMapper.toEntity(dto));
        return ResponseEntity.ok(BloodGroupMapper.toDto(saved));
    }


    @GetMapping
    public List<BloodGroupResponseDto> getAllTests() {
        return bloodGroupService.findAllByIsDeletedFalse().stream()
                .map(BloodGroupMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodGroupResponseDto> getById(@PathVariable Integer id) {
        BloodGroup bloodGroup = bloodGroupService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(BloodGroupMapper.toDto(bloodGroup));
    }


    @PutMapping("/{id}")
    public ResponseEntity<BloodGroupResponseDto> update(
            @PathVariable Integer id,
            @Valid@RequestBody BloodGroupRequestDto dto) {

        BloodGroup updated = bloodGroupService.update(id, dto);
        return ResponseEntity.ok(BloodGroupMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bloodGroupService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
