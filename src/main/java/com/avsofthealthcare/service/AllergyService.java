package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.AllergyRequestDTO;
import com.avsofthealthcare.dto.AllergyResponseDTO;

import java.util.List;

public interface AllergyService {

    AllergyResponseDTO addAllergy(Long userId, AllergyRequestDTO request);

    List<AllergyResponseDTO> getAllergiesByUser(Long userId);

    AllergyResponseDTO getAllergyById(Long userId, Long allergyId);

    AllergyResponseDTO updateAllergy(Long userId, Long allergyId, AllergyRequestDTO request);

    void deleteAllergy(Long userId, Long allergyId);
}
