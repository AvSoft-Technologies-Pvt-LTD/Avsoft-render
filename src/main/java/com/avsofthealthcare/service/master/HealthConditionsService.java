package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.HealthConditionsRequestDto;
import com.avsofthealthcare.entity.master.HealthConditions;

import java.util.List;

public interface HealthConditionsService {
    HealthConditions create(HealthConditionsRequestDto dto);
    List<HealthConditions> findAllByIsDeletedFalse();
    HealthConditions findById(Integer id);
    HealthConditions findByIdAndIsDeletedFalse(Integer id);
    HealthConditions update(Integer id, HealthConditionsRequestDto dto);
    void delete(Integer id);
}
