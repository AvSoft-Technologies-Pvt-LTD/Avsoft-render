package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.ScanServicesRequestDto;
import com.avsofthealthcare.entity.master.ScanServices;

import java.util.List;

public interface ScanServicesService {
    ScanServices create(ScanServicesRequestDto dto);
    List<ScanServices> findAllByIsDeletedFalse();
    ScanServices findById(Integer id);
    ScanServices findByIdAndIsDeletedFalse(Integer id);
    ScanServices update(Integer id, ScanServicesRequestDto dto);
    void delete(Integer id);
}
