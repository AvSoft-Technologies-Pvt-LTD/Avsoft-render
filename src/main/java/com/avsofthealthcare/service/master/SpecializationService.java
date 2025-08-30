package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.SpecializationRequestDto;
import com.avsofthealthcare.dto.master.SpecializationResponseDto;
import com.avsofthealthcare.entity.master.Specialization;

import java.util.List;

public interface SpecializationService {
    Specialization create(SpecializationRequestDto dto);
    List<Specialization> findAllByIsDeletedFalse();
    Specialization findById(Integer id);
    Specialization findByIdAndIsDeletedFalse(Integer id);
    Specialization update(Integer id, SpecializationRequestDto dto);
    void delete(Integer id);
	List<SpecializationResponseDto> getSpecializationsByPracticeTypeId(Integer practiceTypeId);
}

