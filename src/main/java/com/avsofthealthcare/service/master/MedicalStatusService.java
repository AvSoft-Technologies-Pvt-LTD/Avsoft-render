package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.MedicalStatusRequestDto;
import com.avsofthealthcare.entity.master.MedicalStatus;

import java.util.List;

public interface MedicalStatusService {
    MedicalStatus create(MedicalStatusRequestDto dto);
    List<MedicalStatus> findAllByIsDeletedFalse();
    MedicalStatus findById(Integer id);
    MedicalStatus findByIdAndIsDeletedFalse(Integer id);
    MedicalStatus update(Integer id, MedicalStatusRequestDto dto);
    void delete(Integer id);
}
