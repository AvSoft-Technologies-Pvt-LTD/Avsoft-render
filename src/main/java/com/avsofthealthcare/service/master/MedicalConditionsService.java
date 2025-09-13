package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.MedicalConditionsRequestDto;
import com.avsofthealthcare.entity.master.MedicalConditions;

import java.util.List;

public interface MedicalConditionsService {
    MedicalConditions create(MedicalConditionsRequestDto dto);
    List<MedicalConditions> findAllByIsDeletedFalse();
    MedicalConditions findById(Integer id);
    MedicalConditions findByIdAndIsDeletedFalse(Integer id);
    MedicalConditions update(Integer id, MedicalConditionsRequestDto dto);
    void delete(Integer id);
}

