package com.avsofthealthcare.service.impl.master;

import com.avsofthealthcare.dto.master.BloodGroupRequestDto;
import com.avsofthealthcare.entity.master.BloodGroup;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.BloodGroupMapper;
import com.avsofthealthcare.repository.master.BloodGroupRepository;
import com.avsofthealthcare.service.master.BloodGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BloodGroupServiceImpl implements BloodGroupService {

    @Autowired
    private BloodGroupRepository bloodGroupRepository;


    @Override
    public BloodGroup create(BloodGroupRequestDto dto) {
        BloodGroup test = BloodGroupMapper.toEntity(dto);
        return bloodGroupRepository.save(test);
    }

    @Override
    public List<BloodGroup> findAllByIsDeletedFalse() {
        return bloodGroupRepository.findAllByIsDeletedFalse();
    }


    @Override
    public BloodGroup findById(Integer id) {
        return bloodGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blood Group not found with id: " + id));
    }

    @Override
    public BloodGroup findByIdAndIsDeletedFalse(Integer id) {
        return bloodGroupRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blood Group not found with id: " + id));
    }

    @Override
    public BloodGroup update(Integer id, BloodGroupRequestDto dto) {
        BloodGroup bloodGroupexisting = findByIdAndIsDeletedFalse(id);
        bloodGroupexisting.setBloodGroupName(dto.getBloodGroupName());
        bloodGroupexisting.setDescription(dto.getDescription());
        bloodGroupexisting.setActive(dto.getActive());

        // Manually set update fields
        bloodGroupexisting.setUpdatedBy("admin"); // replace with dynamic username if available
        bloodGroupexisting.setUpdatedAt(LocalDateTime.now());
        return bloodGroupRepository.save(bloodGroupexisting);
    }


    @Override
    public void delete(Integer id) {
        BloodGroup existing = findById(id);
        existing.setIsDeleted(true);
        bloodGroupRepository.save(existing);
    }


}

