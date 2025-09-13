package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.BloodGroupRequestDto;
import com.avsofthealthcare.entity.master.BloodGroup;

import java.util.List;

public interface BloodGroupService {
    BloodGroup create(BloodGroupRequestDto dto);
    List<BloodGroup> findAllByIsDeletedFalse();
    BloodGroup findById(Integer id);
    BloodGroup findByIdAndIsDeletedFalse(Integer id);
    BloodGroup update(Integer id, BloodGroupRequestDto dto);
    void delete(Integer id);
}
