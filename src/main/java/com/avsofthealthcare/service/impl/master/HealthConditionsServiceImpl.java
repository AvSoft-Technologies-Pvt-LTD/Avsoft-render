package com.avsofthealthcare.service.impl.master;


import com.avsofthealthcare.dto.master.HealthConditionsRequestDto;
import com.avsofthealthcare.entity.master.HealthConditions;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.HealthConditionsMapper;
import com.avsofthealthcare.repository.master.HealthConditionsRepository;
import com.avsofthealthcare.service.master.HealthConditionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HealthConditionsServiceImpl implements HealthConditionsService {

    @Autowired
    private HealthConditionsRepository healthConditionsRepository;


    @Override
    public HealthConditions create(HealthConditionsRequestDto dto) {
        HealthConditions healthConditions = HealthConditionsMapper.toEntity(dto);
        return healthConditionsRepository.save(healthConditions);
    }

    @Override
    public List<HealthConditions> findAllByIsDeletedFalse() {
        return healthConditionsRepository.findAllByIsDeletedFalse();
    }

    @Override
    public HealthConditions findById(Integer id) {
        return healthConditionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Health Conditions not found with id: " + id));
    }

    @Override
    public HealthConditions findByIdAndIsDeletedFalse(Integer id) {
        return healthConditionsRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Health Conditions not found with id: " + id));
    }

    @Override
    public HealthConditions update(Integer id, HealthConditionsRequestDto dto) {
        HealthConditions healthConditionsExisting = findByIdAndIsDeletedFalse(id);
        healthConditionsExisting.setHealthConditionName(dto.getHealthConditionName());
        healthConditionsExisting.setDescription(dto.getDescription());
        healthConditionsExisting.setActive(dto.getActive());

        // Manually set update fields
        healthConditionsExisting.setUpdatedBy("admin"); // replace with dynamic username if available
        healthConditionsExisting.setUpdatedAt(LocalDateTime.now());
        return healthConditionsRepository.save(healthConditionsExisting);
    }

    @Override
    public void delete(Integer id) {
        HealthConditions existing = findById(id);
        existing.setIsDeleted(true);
        healthConditionsRepository.save(existing);
    }
}


