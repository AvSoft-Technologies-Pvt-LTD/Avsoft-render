package com.avsofthealthcare.controller.master;

import com.avsofthealthcare.dto.master.GenderRequestDto;
import com.avsofthealthcare.dto.master.GenderResponseDto;
import com.avsofthealthcare.entity.master.Gender;
import com.avsofthealthcare.mapper.master.GenderMapper;
import com.avsofthealthcare.repository.master.GenderRepository;
import com.avsofthealthcare.service.master.GenderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/gender")
@Tag(name = "Gender", description = "CRUD for Gender")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @Autowired
    private GenderRepository genderRepository;


    @PostMapping
    public ResponseEntity<GenderResponseDto> createTest(@Valid @RequestBody GenderRequestDto dto) {
        Gender saved = genderRepository.save(GenderMapper.toEntity(dto));
        return ResponseEntity.ok(GenderMapper.toDto(saved));
    }


    @GetMapping
    public List<GenderResponseDto> getAllTests() {
        return genderService.findAllByIsDeletedFalse().stream()
                .map(GenderMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenderResponseDto> getById(@PathVariable Integer id) {
        Gender bloodGroup = genderService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(GenderMapper.toDto(bloodGroup));
    }


    @PutMapping("/{id}")
    public ResponseEntity<GenderResponseDto> update(
            @PathVariable Integer id,
            @Valid@RequestBody GenderRequestDto dto) {

        Gender updated = genderService.update(id, dto);
        return ResponseEntity.ok(GenderMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        genderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}


