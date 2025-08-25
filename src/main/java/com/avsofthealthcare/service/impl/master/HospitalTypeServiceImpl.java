package com.avsofthealthcare.service.impl.master;


import com.avsofthealthcare.dto.master.HospitalTypeRequestDto;
import com.avsofthealthcare.entity.master.HospitalType;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.HospitalTypeMapper;
import com.avsofthealthcare.repository.master.HospitalTypeRepository;
import com.avsofthealthcare.service.master.HospitalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HospitalTypeServiceImpl implements HospitalTypeService {

    @Autowired
    private HospitalTypeRepository hospitalTypeRepository;


    @Override
    public HospitalType create(HospitalTypeRequestDto dto) {
        HospitalType hospitalType = HospitalTypeMapper.toEntity(dto);
        return hospitalTypeRepository.save(hospitalType);
    }

    @Override
    public List<HospitalType> findAllByIsDeletedFalse() {
        return hospitalTypeRepository.findAllByIsDeletedFalse();
    }

    @Override
    public HospitalType findById(Integer id) {
        return hospitalTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital Type not found with id: " + id));
    }

    @Override
    public HospitalType findByIdAndIsDeletedFalse(Integer id) {
        return hospitalTypeRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hospital Type not found with id: " + id));
    }

    @Override
    public HospitalType update(Integer id, HospitalTypeRequestDto dto) {
        HospitalType hospitalTypeExisting = findByIdAndIsDeletedFalse(id);
        hospitalTypeExisting.setHospitalTypeName(dto.getHospitalTypeName());
        hospitalTypeExisting.setDescription(dto.getDescription());
        hospitalTypeExisting.setActive(dto.getActive());

        // Manually set update fields
        hospitalTypeExisting.setUpdatedBy("admin"); // replace with dynamic username if available
        hospitalTypeExisting.setUpdatedAt(LocalDateTime.now());
        return hospitalTypeRepository.save(hospitalTypeExisting);
    }

    @Override
    public void delete(Integer id) {
        HospitalType existing = findById(id);
        existing.setIsDeleted(true);
        hospitalTypeRepository.save(existing);
    }
}



