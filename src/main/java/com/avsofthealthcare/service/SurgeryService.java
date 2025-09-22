package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.SurgeryRequestDTO;
import com.avsofthealthcare.dto.SurgeryResponseDTO;

import java.util.List;

public interface SurgeryService {

    SurgeryResponseDTO addSurgery(Long userId, SurgeryRequestDTO request);

    List<SurgeryResponseDTO> getSurgeriesByUser(Long userId);

    SurgeryResponseDTO getSurgeryById(Long userId, Long surgeryId);

    SurgeryResponseDTO updateSurgery(Long userId, Long surgeryId, SurgeryRequestDTO request);

    void deleteSurgery(Long userId, Long surgeryId);
}
