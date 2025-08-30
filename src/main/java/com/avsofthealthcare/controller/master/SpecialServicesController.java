package com.avsofthealthcare.controller.master;

import com.avsofthealthcare.dto.master.SpecialServicesRequestDto;
import com.avsofthealthcare.dto.master.SpecialServicesResponseDto;
import com.avsofthealthcare.entity.master.SpecialServices;
import com.avsofthealthcare.mapper.master.SpecialServicesMapper;
import com.avsofthealthcare.repository.master.SpecialServicesRepository;
import com.avsofthealthcare.service.master.SpecialServicesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/specialServices")
@Tag(name = "Special Services", description = "CRUD for Special Services")
public class SpecialServicesController {

    @Autowired
    private SpecialServicesService specialServicesService;

    @Autowired
    private SpecialServicesRepository specialServicesRepository;


    @PostMapping
    public ResponseEntity<SpecialServicesResponseDto> createTest(@Valid @RequestBody SpecialServicesRequestDto dto) {
        SpecialServices saved = specialServicesRepository.save(SpecialServicesMapper.toEntity(dto));
        return ResponseEntity.ok(SpecialServicesMapper.toDto(saved));
    }


    @GetMapping
    public List<SpecialServicesResponseDto> getAllTests() {
        return specialServicesService.findAllByIsDeletedFalse().stream()
                .map(SpecialServicesMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialServicesResponseDto> getById(@PathVariable Integer id) {
        SpecialServices bloodGroup = specialServicesService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(SpecialServicesMapper.toDto(bloodGroup));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SpecialServicesResponseDto> update(
            @PathVariable Integer id,
            @Valid@RequestBody SpecialServicesRequestDto dto) {

        SpecialServices updated = specialServicesService.update(id, dto);
        return ResponseEntity.ok(SpecialServicesMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        specialServicesService.delete(id);
        return ResponseEntity.noContent().build();
    }

}


