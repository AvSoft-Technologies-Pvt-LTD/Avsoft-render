package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.GenderRequestDto;
import com.avsofthealthcare.entity.master.Gender;

import java.util.List;

public interface GenderService {
    Gender create(GenderRequestDto dto);
    List<Gender> findAllByIsDeletedFalse();
    Gender findById(Integer id);
    Gender findByIdAndIsDeletedFalse(Integer id);
    Gender update(Integer id, GenderRequestDto dto);
    void delete(Integer id);
}

