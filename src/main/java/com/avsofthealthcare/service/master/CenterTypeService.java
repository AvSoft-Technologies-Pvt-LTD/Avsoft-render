package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.CenterTypeRequestDto;
import com.avsofthealthcare.entity.master.CenterType;

import java.util.List;

public interface CenterTypeService {
    CenterType create(CenterTypeRequestDto dto);
    List<CenterType> findAllByIsDeletedFalse();
    CenterType findById(Integer id);
    CenterType findByIdAndIsDeletedFalse(Integer id);
    CenterType update(Integer id, CenterTypeRequestDto dto);
    void delete(Integer id);
}
