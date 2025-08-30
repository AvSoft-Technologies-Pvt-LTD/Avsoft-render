package com.avsofthealthcare.controller.master;

import com.avsofthealthcare.dto.master.ScanServicesRequestDto;
import com.avsofthealthcare.dto.master.ScanServicesResponseDto;
import com.avsofthealthcare.entity.master.ScanServices;
import com.avsofthealthcare.mapper.master.ScanServicesMapper;
import com.avsofthealthcare.repository.master.ScanServicesRepository;
import com.avsofthealthcare.service.master.ScanServicesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/scanServices")
@Tag(name = "Scan Services", description = "CRUD for Scan Services")
public class ScanServicesController {

    @Autowired
    private ScanServicesService scanServicesService;

    @Autowired
    private ScanServicesRepository scanServicesRepository;


    @PostMapping
    public ResponseEntity<ScanServicesResponseDto> createTest(@Valid @RequestBody ScanServicesRequestDto dto) {
        ScanServices saved = scanServicesRepository.save(ScanServicesMapper.toEntity(dto));
        return ResponseEntity.ok(ScanServicesMapper.toDto(saved));
    }


    @GetMapping
    public List<ScanServicesResponseDto> getAllTests() {
        return scanServicesService.findAllByIsDeletedFalse().stream()
                .map(ScanServicesMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScanServicesResponseDto> getById(@PathVariable Integer id) {
        ScanServices bloodGroup = scanServicesService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(ScanServicesMapper.toDto(bloodGroup));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ScanServicesResponseDto> update(
            @PathVariable Integer id,
            @Valid@RequestBody ScanServicesRequestDto dto) {

        ScanServices updated = scanServicesService.update(id, dto);
        return ResponseEntity.ok(ScanServicesMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        scanServicesService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

