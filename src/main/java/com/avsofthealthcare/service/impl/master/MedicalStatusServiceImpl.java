package com.avsofthealthcare.service.impl.master;


import com.avsofthealthcare.dto.master.MedicalStatusRequestDto;
import com.avsofthealthcare.entity.master.MedicalStatus;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.MedicalStatusMapper;
import com.avsofthealthcare.repository.master.MedicalStatusRepository;
import com.avsofthealthcare.service.master.MedicalStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicalStatusServiceImpl implements MedicalStatusService {

    @Autowired
    private MedicalStatusRepository medicalStatusRepository;


    @Override
    public MedicalStatus create(MedicalStatusRequestDto dto) {
        MedicalStatus medicalStatus = MedicalStatusMapper.toEntity(dto);
        return medicalStatusRepository.save(medicalStatus);
    }

    @Override
    public List<MedicalStatus> findAllByIsDeletedFalse() {
        return medicalStatusRepository.findAllByIsDeletedFalse();
    }

    @Override
    public MedicalStatus findById(Integer id) {
        return medicalStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Status not found with id: " + id));
    }

    @Override
    public MedicalStatus findByIdAndIsDeletedFalse(Integer id) {
        return medicalStatusRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Status not found with id: " + id));
    }

    @Override
    public MedicalStatus update(Integer id, MedicalStatusRequestDto dto) {
        MedicalStatus medicalStatusExisting = findByIdAndIsDeletedFalse(id);
        medicalStatusExisting.setStatusName(dto.getStatusName());
        medicalStatusExisting.setDescription(dto.getDescription());
        medicalStatusExisting.setActive(dto.getActive());

        // Manually set update fields
        medicalStatusExisting.setUpdatedBy("admin"); // replace with dynamic username if available
        medicalStatusExisting.setUpdatedAt(LocalDateTime.now());
        return medicalStatusRepository.save(medicalStatusExisting);
    }

    @Override
    public void delete(Integer id) {
        MedicalStatus existing = findById(id);
        existing.setIsDeleted(true);
        medicalStatusRepository.save(existing);
    }
}





