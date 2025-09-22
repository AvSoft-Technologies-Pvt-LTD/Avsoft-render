package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.AllergyRequestDTO;
import com.avsofthealthcare.dto.AllergyResponseDTO;
import com.avsofthealthcare.entity.Allergy;
import com.avsofthealthcare.repository.AllergyRepository;
import com.avsofthealthcare.service.AllergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllergyServiceImpl implements AllergyService {

    private final AllergyRepository allergyRepository;

    @Override
    public AllergyResponseDTO addAllergy(Long userId, AllergyRequestDTO request) {
        Allergy allergy = new Allergy();
        allergy.setUserId(userId);
        allergy.setAllergyName(request.getAllergyName());
        allergy.setSeverity(request.getSeverity());
        allergy.setNotes(request.getNotes());
        allergy.setDiagnosedDate(request.getDiagnosedDate());

        allergy = allergyRepository.save(allergy);
        return toResponseDTO(allergy);
    }

    @Override
    public List<AllergyResponseDTO> getAllergiesByUser(Long userId) {
        return allergyRepository.findByUserId(userId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AllergyResponseDTO getAllergyById(Long userId, Long allergyId) {
        Allergy allergy = allergyRepository.findById(allergyId)
                .filter(a -> a.getUserId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Allergy not found"));
        return toResponseDTO(allergy);
    }

    @Override
    public AllergyResponseDTO updateAllergy(Long userId, Long allergyId, AllergyRequestDTO request) {
        Allergy allergy = allergyRepository.findById(allergyId)
                .filter(a -> a.getUserId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Allergy not found"));

        allergy.setAllergyName(request.getAllergyName());
        allergy.setSeverity(request.getSeverity());
        allergy.setNotes(request.getNotes());
        allergy.setDiagnosedDate(request.getDiagnosedDate());

        allergy = allergyRepository.save(allergy);
        return toResponseDTO(allergy);
    }

    @Override
    public void deleteAllergy(Long userId, Long allergyId) {
        Allergy allergy = allergyRepository.findById(allergyId)
                .filter(a -> a.getUserId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Allergy not found"));
        allergyRepository.delete(allergy);
    }

    private AllergyResponseDTO toResponseDTO(Allergy allergy) {
        AllergyResponseDTO dto = new AllergyResponseDTO();
        dto.setId(allergy.getId());
        dto.setUserId(allergy.getUserId());
        dto.setAllergyName(allergy.getAllergyName());
        dto.setSeverity(allergy.getSeverity());
        dto.setNotes(allergy.getNotes());
        dto.setDiagnosedDate(allergy.getDiagnosedDate());
        return dto;
    }
}
