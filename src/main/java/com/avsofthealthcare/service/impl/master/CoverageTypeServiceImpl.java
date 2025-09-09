package com.avsofthealthcare.service.impl.master;

import com.avsofthealthcare.dto.master.CoverageTypeRequestDto;
import com.avsofthealthcare.entity.master.CoverageType;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.CoverageTypeMapper;
import com.avsofthealthcare.repository.master.CoverageTypeRepository;
import com.avsofthealthcare.service.master.CoverageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CoverageTypeServiceImpl implements CoverageTypeService {

    @Autowired
    private CoverageTypeRepository coverageTypeRepository;


    @Override
    public CoverageType create(CoverageTypeRequestDto dto) {
        CoverageType coverageType = CoverageTypeMapper.toEntity(dto);
        return coverageTypeRepository.save(coverageType);
    }

    @Override
    public List<CoverageType> findAllByIsDeletedFalse() {
        return coverageTypeRepository.findAllByIsDeletedFalse();
    }

    @Override
    public CoverageType findById(Integer id) {
        return coverageTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coverage Type not found with id: " + id));
    }

    @Override
    public CoverageType findByIdAndIsDeletedFalse(Integer id) {
        return coverageTypeRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coverage Type not found with id: " + id));
    }

    @Override
    public CoverageType update(Integer id, CoverageTypeRequestDto dto) {
        CoverageType coverageTypeExisting = findByIdAndIsDeletedFalse(id);
        coverageTypeExisting.setCoverageTypeName(dto.getCoverageTypeName());
        coverageTypeExisting.setDescription(dto.getDescription());
        coverageTypeExisting.setActive(dto.getActive());

        // Manually set update fields
        coverageTypeExisting.setUpdatedBy("admin"); // replace with dynamic username if available
        coverageTypeExisting.setUpdatedAt(LocalDateTime.now());
        return coverageTypeRepository.save(coverageTypeExisting);
    }

    @Override
    public void delete(Integer id) {
        CoverageType existing = findById(id);
        existing.setIsDeleted(true);
        coverageTypeRepository.save(existing);
    }
}

