package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.LabScanRequestDTO;
import com.avsofthealthcare.dto.LabScanResponseDTO;

import java.util.List;

public interface LabScanService {
    List<LabScanResponseDTO> getAllScans();

    LabScanResponseDTO getScan(Long id);

    LabScanResponseDTO addScan(LabScanRequestDTO dto);

    LabScanResponseDTO updateScan(Long id, LabScanRequestDTO dto);

    void deleteScan(Long id);
}
