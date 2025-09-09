package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.SpecialServicesRequestDto;
import com.avsofthealthcare.entity.master.SpecialServices;

import java.util.List;

public interface SpecialServicesService {
    SpecialServices create(SpecialServicesRequestDto dto);
    List<SpecialServices> findAllByIsDeletedFalse();
    SpecialServices findById(Integer id);
    SpecialServices findByIdAndIsDeletedFalse(Integer id);
    SpecialServices update(Integer id, SpecialServicesRequestDto dto);
    void delete(Integer id);
}

