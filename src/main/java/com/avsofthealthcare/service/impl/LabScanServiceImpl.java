package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.LabScanRequestDTO;
import com.avsofthealthcare.dto.LabScanResponseDTO;
import com.avsofthealthcare.entity.LabScan;
import com.avsofthealthcare.repository.LabScanRepository;
import com.avsofthealthcare.service.LabScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabScanServiceImpl implements LabScanService {

    private final LabScanRepository repository;

    @Override
    public List<LabScanResponseDTO> getAllScans() {
        return repository.findAll().stream().map(this::toResponseDTO).toList();
    }

    @Override
    public LabScanResponseDTO getScan(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Scan not found"));
    }

    @Override
    public LabScanResponseDTO addScan(LabScanRequestDTO dto) {
        LabScan scan = toEntity(dto);
        return toResponseDTO(repository.save(scan));
    }

    @Override
    public LabScanResponseDTO updateScan(Long id, LabScanRequestDTO dto) {
        LabScan scan = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scan not found"));
//        scan.setCode(dto.getCode());
        scan.setScanname(dto.getScanname());
        scan.setDescription(dto.getDescription());
        scan.setPrice(dto.getPrice());
        scan.setLabName(dto.getLabName());
        scan.setScanType(dto.getScanType()); // ✅ Added
        return toResponseDTO(repository.save(scan));
    }

    @Override
    public void deleteScan(Long id) {
        repository.deleteById(id);
    }

    // Helpers
    private LabScanResponseDTO toResponseDTO(LabScan scan) {
        LabScanResponseDTO dto = new LabScanResponseDTO();
        dto.setId(scan.getId());
//        dto.setCode(scan.getCode());
        dto.setScanname(scan.getScanname());
        dto.setDescription(scan.getDescription());
        dto.setPrice(scan.getPrice());
        dto.setLabName(scan.getLabName());
        dto.setScanType(scan.getScanType()); // ✅ Added
        return dto;
    }

    private LabScan toEntity(LabScanRequestDTO dto) {
        LabScan scan = new LabScan();
//        scan.setCode(dto.getCode());
        scan.setScanname(dto.getScanname());
        scan.setDescription(dto.getDescription());
        scan.setPrice(dto.getPrice());
        scan.setLabName(dto.getLabName());
        scan.setScanType(dto.getScanType()); // ✅ Added
        return scan;
    }
}
