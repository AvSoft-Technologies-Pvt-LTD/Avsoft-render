package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.LabTestRequestDTO;
import com.avsofthealthcare.dto.LabTestResponseDTO;
import com.avsofthealthcare.entity.LabTest;
import com.avsofthealthcare.repository.LabTestRepository;
import com.avsofthealthcare.service.LabTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabTestServiceImpl implements LabTestService {

    private final LabTestRepository repository;

    @Override
    public List<LabTestResponseDTO> getAllTests() {
        return repository.findAll().stream().map(this::toResponseDTO).toList();
    }

    @Override
    public LabTestResponseDTO getTest(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Test not found"));
    }

    @Override
    public LabTestResponseDTO addTest(LabTestRequestDTO dto) {
        LabTest test = toEntity(dto);
        return toResponseDTO(repository.save(test));
    }

    @Override
    public LabTestResponseDTO updateTest(Long id, LabTestRequestDTO dto) {
        LabTest test = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));
//        test.setCode(dto.getCode());
        test.setTestname(dto.getTestname());
        test.setDescription(dto.getDescription());
        test.setPrice(dto.getPrice());
        test.setLabName(dto.getLabName());
        test.setTestType(dto.getTestType()); // ✅ Added
        return toResponseDTO(repository.save(test));
    }

    @Override
    public void deleteTest(Long id) {
        repository.deleteById(id);
    }

    // Helpers
    private LabTestResponseDTO toResponseDTO(LabTest test) {
        LabTestResponseDTO dto = new LabTestResponseDTO();
        dto.setId(test.getId());
//        dto.setCode(test.getCode());
        dto.setTestname(test.getTestname());
        dto.setDescription(test.getDescription());
        dto.setPrice(test.getPrice());
        dto.setLabName(test.getLabName());
        dto.setTestType(test.getTestType()); // ✅ Added
        return dto;
    }

    private LabTest toEntity(LabTestRequestDTO dto) {
        LabTest test = new LabTest();
//        test.setCode(dto.getCode());
        test.setTestname(dto.getTestname());
        test.setDescription(dto.getDescription());
        test.setPrice(dto.getPrice());
        test.setLabName(dto.getLabName());
        test.setTestType(dto.getTestType()); // ✅ Added
        return test;
    }
}
