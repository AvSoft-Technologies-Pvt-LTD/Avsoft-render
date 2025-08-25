package com.avsofthealthcare.service.impl.master;

import com.avsofthealthcare.dto.master.MedicalConditionsRequestDto;
import com.avsofthealthcare.entity.master.MedicalConditions;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.MedicalConditionsMapper;
import com.avsofthealthcare.repository.master.MedicalConditionsRepository;
import com.avsofthealthcare.service.master.MedicalConditionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicalConditionsServiceImpl implements MedicalConditionsService {

    @Autowired
    private MedicalConditionsRepository medicalConditionsRepository;


    @Override
    public MedicalConditions create(MedicalConditionsRequestDto dto) {
        MedicalConditions medicalConditions = MedicalConditionsMapper.toEntity(dto);
        return medicalConditionsRepository.save(medicalConditions);
    }

    @Override
    public List<MedicalConditions> findAllByIsDeletedFalse() {
        return medicalConditionsRepository.findAllByIsDeletedFalse();
    }

    @Override
    public MedicalConditions findById(Integer id) {
        return medicalConditionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Conditions not found with id: " + id));
    }

    @Override
    public MedicalConditions findByIdAndIsDeletedFalse(Integer id) {
        return medicalConditionsRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Conditions not found with id: " + id));
    }

    @Override
    public MedicalConditions update(Integer id, MedicalConditionsRequestDto dto) {
        MedicalConditions medicalConditionsExisting = findByIdAndIsDeletedFalse(id);
        medicalConditionsExisting.setConditionName(dto.getConditionName());
        medicalConditionsExisting.setDescription(dto.getDescription());
        medicalConditionsExisting.setActive(dto.getActive());

        // Manually set update fields
        medicalConditionsExisting.setUpdatedBy("admin"); // replace with dynamic username if available
        medicalConditionsExisting.setUpdatedAt(LocalDateTime.now());
        return medicalConditionsRepository.save(medicalConditionsExisting);
    }

    @Override
    public void delete(Integer id) {
        MedicalConditions existing = findById(id);
        existing.setIsDeleted(true);
        medicalConditionsRepository.save(existing);
    }
}




