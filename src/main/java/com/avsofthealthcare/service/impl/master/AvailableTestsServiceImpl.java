package com.avsofthealthcare.service.impl.master;

import com.avsofthealthcare.dto.master.AvailableTestRequestDto;
import com.avsofthealthcare.entity.master.AvailableTests;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.AvailableTestMapper;
import com.avsofthealthcare.repository.master.AvailableTestsRepository;
import com.avsofthealthcare.service.master.AvailableTestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AvailableTestsServiceImpl implements AvailableTestsService {

    @Autowired
    private AvailableTestsRepository availableTestsRepository;


    @Override
    public AvailableTests create(AvailableTestRequestDto dto) {
        AvailableTests test = AvailableTestMapper.toEntity(dto);
        return availableTestsRepository.save(test);
    }

    @Override
    public List<AvailableTests> findAllByIsDeletedFalse() {
        return availableTestsRepository.findAllByIsDeletedFalse();
    }


    @Override
    public AvailableTests findById(Integer id) {
        return availableTestsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Available Test not found with id: " + id));
    }

    @Override
    public AvailableTests findByIdAndIsDeletedFalse(Integer id) {
        return availableTestsRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Available Test not found with id: " + id));
    }

    @Override
    public AvailableTests update(Integer id, AvailableTestRequestDto dto) {
        AvailableTests existing = findByIdAndIsDeletedFalse(id);
        existing.setTestName(dto.getTestName());
        existing.setDescription(dto.getDescription());
        existing.setActive(dto.getActive());

        // Manually set update fields
        existing.setUpdatedBy("admin"); // replace with dynamic username if available
        existing.setUpdatedAt(LocalDateTime.now());
        return availableTestsRepository.save(existing);
    }


    @Override
    public void delete(Integer id) {
        AvailableTests existing = findById(id);
        existing.setIsDeleted(true);
        availableTestsRepository.save(existing);
    }


}
