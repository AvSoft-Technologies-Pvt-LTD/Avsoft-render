package com.avsofthealthcare.controller.master;

import com.avsofthealthcare.dto.master.CenterTypeRequestDto;
import com.avsofthealthcare.dto.master.CenterTypeResponseDto;
import com.avsofthealthcare.entity.master.CenterType;
import com.avsofthealthcare.mapper.master.CenterTypeMapper;
import com.avsofthealthcare.repository.master.CenterTypeRepository;
import com.avsofthealthcare.service.master.CenterTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/center-type")
@Tag(name = "Center Type", description = "CRUD for Center Type")
public class CenterTypeController {

    @Autowired
    private CenterTypeService centerTypeService;

    @Autowired
    private CenterTypeRepository centerTypeRepository;


    @PostMapping
    public ResponseEntity<CenterTypeResponseDto> createTest(@Valid @RequestBody CenterTypeRequestDto dto) {
        CenterType saved = centerTypeRepository.save(CenterTypeMapper.toEntity(dto));
        return ResponseEntity.ok(CenterTypeMapper.toDto(saved));
    }


    @GetMapping
    public List<CenterTypeResponseDto> getAllTests() {
        return centerTypeService.findAllByIsDeletedFalse().stream()
                .map(CenterTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CenterTypeResponseDto> getById(@PathVariable Integer id) {
        CenterType bloodGroup = centerTypeService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(CenterTypeMapper.toDto(bloodGroup));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CenterTypeResponseDto> update(
            @PathVariable Integer id,
            @Valid@RequestBody CenterTypeRequestDto dto) {

        CenterType updated = centerTypeService.update(id, dto);
        return ResponseEntity.ok(CenterTypeMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        centerTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

