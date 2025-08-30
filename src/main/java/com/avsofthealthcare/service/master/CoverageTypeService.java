package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.CoverageTypeRequestDto;
import com.avsofthealthcare.entity.master.CoverageType;

import java.util.List;

public interface CoverageTypeService {
    CoverageType create(CoverageTypeRequestDto dto);
    List<CoverageType> findAllByIsDeletedFalse();
    CoverageType findById(Integer id);
    CoverageType findByIdAndIsDeletedFalse(Integer id);
    CoverageType update(Integer id, CoverageTypeRequestDto dto);
    void delete(Integer id);
}
