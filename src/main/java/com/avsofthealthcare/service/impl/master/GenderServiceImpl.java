package com.avsofthealthcare.service.impl.master;



import com.avsofthealthcare.dto.master.GenderRequestDto;
import com.avsofthealthcare.entity.master.Gender;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.GenderMapper;
import com.avsofthealthcare.repository.master.GenderRepository;
import com.avsofthealthcare.service.master.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GenderServiceImpl implements GenderService {

    @Autowired
    private GenderRepository genderRepository;


    @Override
    public Gender create(GenderRequestDto dto) {
        Gender gender = GenderMapper.toEntity(dto);
        return genderRepository.save(gender);
    }

    @Override
    public List<Gender> findAllByIsDeletedFalse() {
        return genderRepository.findAllByIsDeletedFalse();
    }

    @Override
    public Gender findById(Integer id) {
        return genderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gender not found with id: " + id));
    }

    @Override
    public Gender findByIdAndIsDeletedFalse(Integer id) {
        return genderRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gender not found with id: " + id));
    }

    @Override
    public Gender update(Integer id, GenderRequestDto dto) {
        Gender genderExisting = findByIdAndIsDeletedFalse(id);
        genderExisting.setGenderName(dto.getGenderName());
        genderExisting.setDescription(dto.getDescription());
        genderExisting.setActive(dto.getActive());

        // Manually set update fields
        genderExisting.setUpdatedBy("admin"); // replace with dynamic username if available
        genderExisting.setUpdatedAt(LocalDateTime.now());
        return genderRepository.save(genderExisting);
    }

    @Override
    public void delete(Integer id) {
        Gender existing = findById(id);
        existing.setIsDeleted(true);
        genderRepository.save(existing);
    }
}


