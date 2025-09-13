package com.avsofthealthcare.controller.master;

import com.avsofthealthcare.dto.master.CertificateTypesRequestDto;
import com.avsofthealthcare.dto.master.CertificateTypesResponseDto;
import com.avsofthealthcare.entity.master.CertificateTypes;
import com.avsofthealthcare.mapper.master.CertificateTypesMapper;
import com.avsofthealthcare.repository.master.CertificateTypesRepository;
import com.avsofthealthcare.service.master.CertificateTypesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/certificate-type")
@Tag(name = "Certificate Type", description = "CRUD for Certificate Type")
public class CertificateTypesController {

    @Autowired
    private CertificateTypesService certificateTypesService;

    @Autowired
    private CertificateTypesRepository certificateTypesRepository;


    @PostMapping
    public ResponseEntity<CertificateTypesResponseDto> createTest(@RequestBody CertificateTypesRequestDto dto) {
        CertificateTypes saved = certificateTypesRepository.save(CertificateTypesMapper.toEntity(dto));
        return ResponseEntity.ok(CertificateTypesMapper.toDto(saved));
    }


    @GetMapping
    public List<CertificateTypesResponseDto> getAllTests() {
        return certificateTypesService.findAllByIsDeletedFalse().stream()
                .map(CertificateTypesMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateTypesResponseDto> getById(@PathVariable Integer id) {
        CertificateTypes bloodGroup = certificateTypesService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(CertificateTypesMapper.toDto(bloodGroup));
    }


    @PutMapping("/{id}")
    public ResponseEntity<CertificateTypesResponseDto> update(
            @PathVariable Integer id,
            @RequestBody CertificateTypesRequestDto dto) {

        CertificateTypes updated = certificateTypesService.update(id, dto);
        return ResponseEntity.ok(CertificateTypesMapper.toDto(updated));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        certificateTypesService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
