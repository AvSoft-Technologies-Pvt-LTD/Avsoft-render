package com.avsofthealthcare.service.impl.master;

import com.avsofthealthcare.dto.master.PracticeTypeRequestDto;
import com.avsofthealthcare.entity.master.PracticeType;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.PracticeTypeMapper;
import com.avsofthealthcare.repository.master.PracticeTypeRepository;
import com.avsofthealthcare.service.master.PracticeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PracticeTypeServiceImpl implements PracticeTypeService {

    @Autowired
    private PracticeTypeRepository practiceTypeRepository;


    @Override
    public PracticeType create(PracticeTypeRequestDto dto) {
        PracticeType practiceType = PracticeTypeMapper.toEntity(dto);
        return practiceTypeRepository.save(practiceType);
    }

    @Override
    public List<PracticeType> findAllByIsDeletedFalse() {
        return practiceTypeRepository.findAllByIsDeletedFalse();
    }

    @Override
    public PracticeType findById(Integer id) {
        return practiceTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Practice Type not found with id: " + id));
    }

    @Override
    public PracticeType findByIdAndIsDeletedFalse(Integer id) {
        return practiceTypeRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Practice Type not found with id: " + id));
    }

    @Override
    public PracticeType update(Integer id, PracticeTypeRequestDto dto) {
        PracticeType practiceTypeExisting = findByIdAndIsDeletedFalse(id);
        practiceTypeExisting.setPracticeName(dto.getPracticeName());
        practiceTypeExisting.setDescription(dto.getDescription());
        practiceTypeExisting.setActive(dto.getActive());

        // Manually set update fields
        practiceTypeExisting.setUpdatedBy("admin"); // replace with dynamic username if available
        practiceTypeExisting.setUpdatedAt(LocalDateTime.now());
        return practiceTypeRepository.save(practiceTypeExisting);
    }

    @Override
    public void delete(Integer id) {
        PracticeType existing = findById(id);
        existing.setIsDeleted(true);
        practiceTypeRepository.save(existing);
    }
}

