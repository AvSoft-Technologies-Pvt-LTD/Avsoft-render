package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.SurgeryRequestDTO;
import com.avsofthealthcare.dto.SurgeryResponseDTO;
import com.avsofthealthcare.entity.Surgery;
import com.avsofthealthcare.repository.SurgeryRepository;
import com.avsofthealthcare.service.SurgeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurgeryServiceImpl implements SurgeryService {

    private final SurgeryRepository surgeryRepository;

    @Override
    public SurgeryResponseDTO addSurgery(Long userId, SurgeryRequestDTO request) {
        Surgery surgery = new Surgery();
        surgery.setUserId(userId);
        surgery.setSurgeryName(request.getSurgeryName());
        surgery.setSurgeryDate(request.getSurgeryDate());
        surgery.setNotes(request.getNotes());
        surgery = surgeryRepository.save(surgery);
        return toResponseDTO(surgery);
    }

    @Override
    public List<SurgeryResponseDTO> getSurgeriesByUser(Long userId) {
        return surgeryRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SurgeryResponseDTO getSurgeryById(Long userId, Long surgeryId) {
        Surgery surgery = surgeryRepository.findById(surgeryId)
                .filter(s -> s.getUserId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Surgery not found"));
        return toResponseDTO(surgery);
    }

    @Override
    public SurgeryResponseDTO updateSurgery(Long userId, Long surgeryId, SurgeryRequestDTO request) {
        Surgery surgery = surgeryRepository.findById(surgeryId)
                .filter(s -> s.getUserId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Surgery not found"));

        surgery.setSurgeryName(request.getSurgeryName());
        surgery.setSurgeryDate(request.getSurgeryDate());
        surgery.setNotes(request.getNotes());

        surgery = surgeryRepository.save(surgery);
        return toResponseDTO(surgery);
    }

    @Override
    public void deleteSurgery(Long userId, Long surgeryId) {
        Surgery surgery = surgeryRepository.findById(surgeryId)
                .filter(s -> s.getUserId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Surgery not found"));
        surgeryRepository.delete(surgery);
    }

    private SurgeryResponseDTO toResponseDTO(Surgery surgery) {
        SurgeryResponseDTO dto = new SurgeryResponseDTO();
        dto.setId(surgery.getId());
        dto.setUserId(surgery.getUserId());
        dto.setSurgeryName(surgery.getSurgeryName());
        dto.setSurgeryDate(surgery.getSurgeryDate());
        dto.setNotes(surgery.getNotes());
        return dto;
    }
}
