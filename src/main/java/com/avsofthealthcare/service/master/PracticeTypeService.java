package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.PracticeTypeRequestDto;
import com.avsofthealthcare.entity.master.PracticeType;

import java.util.List;

public interface PracticeTypeService {
    PracticeType create(PracticeTypeRequestDto dto);
    List<PracticeType> findAllByIsDeletedFalse();
    PracticeType findById(Integer id);
    PracticeType findByIdAndIsDeletedFalse(Integer id);
    PracticeType update(Integer id, PracticeTypeRequestDto dto);
    void delete(Integer id);
}

