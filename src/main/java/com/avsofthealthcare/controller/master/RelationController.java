package com.avsofthealthcare.controller.master;

import com.avsofthealthcare.dto.master.RelationRequestDto;
import com.avsofthealthcare.dto.master.RelationResponseDto;
import com.avsofthealthcare.entity.master.Relation;
import com.avsofthealthcare.mapper.master.RelationMapper;
import com.avsofthealthcare.repository.master.RelationRepository;
import com.avsofthealthcare.service.master.RelationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/relation")
@Tag(name = "Relation", description = "CRUD for Relation")
public class RelationController {

    @Autowired
    private RelationService relationService;

    @Autowired
    private RelationRepository relationRepository;


    @PostMapping
    public ResponseEntity<RelationResponseDto> createTest(@Valid @RequestBody RelationRequestDto dto) {
        Relation saved = relationRepository.save(RelationMapper.toEntity(dto));
        return ResponseEntity.ok(RelationMapper.toDto(saved));
    }


    @GetMapping
    public List<RelationResponseDto> getAllTests() {
        return relationService.findAllByIsDeletedFalse().stream()
                .map(RelationMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelationResponseDto> getById(@PathVariable Integer id) {
        Relation bloodGroup = relationService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(RelationMapper.toDto(bloodGroup));
    }


    @PutMapping("/{id}")
    public ResponseEntity<RelationResponseDto> update(
            @PathVariable Integer id,
            @Valid@RequestBody RelationRequestDto dto) {

        Relation updated = relationService.update(id, dto);
        return ResponseEntity.ok(RelationMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        relationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
