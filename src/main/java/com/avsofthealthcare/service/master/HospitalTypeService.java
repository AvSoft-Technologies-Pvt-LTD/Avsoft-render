package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.HospitalTypeRequestDto;
import com.avsofthealthcare.entity.master.HospitalType;

import java.util.List;

public interface HospitalTypeService {
    HospitalType create(HospitalTypeRequestDto dto);
    List<HospitalType> findAllByIsDeletedFalse();
    HospitalType findById(Integer id);
    HospitalType findByIdAndIsDeletedFalse(Integer id);
    HospitalType update(Integer id, HospitalTypeRequestDto dto);
    void delete(Integer id);
}

