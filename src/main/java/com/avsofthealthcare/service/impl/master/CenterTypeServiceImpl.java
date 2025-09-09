package com.avsofthealthcare.service.impl.master;

import com.avsofthealthcare.dto.master.CenterTypeRequestDto;
import com.avsofthealthcare.entity.master.CenterType;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.CenterTypeMapper;
import com.avsofthealthcare.repository.master.CenterTypeRepository;
import com.avsofthealthcare.service.master.CenterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CenterTypeServiceImpl implements CenterTypeService {

    @Autowired
    private CenterTypeRepository centerTypeRepository;

    @Override
    public CenterType create(CenterTypeRequestDto dto) {
        CenterType centerType = CenterTypeMapper.toEntity(dto);
        return centerTypeRepository.save(centerType);
    }

    @Override
    public List<CenterType> findAllByIsDeletedFalse() {
        return centerTypeRepository.findAllByIsDeletedFalse();
    }

    @Override
    public CenterType findById(Integer id) {
        return centerTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Center Type not found with id: " + id));
    }

    @Override
    public CenterType findByIdAndIsDeletedFalse(Integer id) {
        return centerTypeRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Center Type not found with id: " + id));
    }

    @Override
    public CenterType update(Integer id, CenterTypeRequestDto dto) {
        CenterType centerTypeExisting = findByIdAndIsDeletedFalse(id);
        centerTypeExisting.setCenterTypeName(dto.getCenterTypeName());
        centerTypeExisting.setDescription(dto.getDescription());
        centerTypeExisting.setActive(dto.getActive());

        // Manually set update fields
        centerTypeExisting.setUpdatedBy("admin"); // replace with dynamic username if available
        centerTypeExisting.setUpdatedAt(LocalDateTime.now());
        return centerTypeRepository.save(centerTypeExisting);
    }

    @Override
    public void delete(Integer id) {
        CenterType existing = findById(id);
        existing.setIsDeleted(true);
        centerTypeRepository.save(existing);
    }
}
