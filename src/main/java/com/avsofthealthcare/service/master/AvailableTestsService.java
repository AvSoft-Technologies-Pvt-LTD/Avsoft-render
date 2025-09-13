package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.AvailableTestRequestDto;
import com.avsofthealthcare.entity.master.AvailableTests;

import java.util.List;

public interface AvailableTestsService {
    AvailableTests create(AvailableTestRequestDto dto);
    List<AvailableTests> findAllByIsDeletedFalse();
    AvailableTests findById(Integer id);
    AvailableTests findByIdAndIsDeletedFalse(Integer id);
    AvailableTests update(Integer id, AvailableTestRequestDto dto);
    void delete(Integer id);
}
