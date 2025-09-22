package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.LabTestRequestDTO;
import com.avsofthealthcare.dto.LabTestResponseDTO;

import java.util.List;

public interface LabTestService {
    List<LabTestResponseDTO> getAllTests();

    LabTestResponseDTO getTest(Long id);

    LabTestResponseDTO addTest(LabTestRequestDTO dto);

    LabTestResponseDTO updateTest(Long id, LabTestRequestDTO dto);

    void deleteTest(Long id);
}
