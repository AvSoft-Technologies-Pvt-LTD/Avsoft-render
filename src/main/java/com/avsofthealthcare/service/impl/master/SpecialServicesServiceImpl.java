package com.avsofthealthcare.service.impl.master;

import com.avsofthealthcare.dto.master.SpecialServicesRequestDto;
import com.avsofthealthcare.entity.master.SpecialServices;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.SpecialServicesMapper;
import com.avsofthealthcare.repository.master.SpecialServicesRepository;
import com.avsofthealthcare.service.master.SpecialServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SpecialServicesServiceImpl implements SpecialServicesService {

    @Autowired
    private SpecialServicesRepository specialServicesRepository;


    @Override
    public SpecialServices create(SpecialServicesRequestDto dto) {
        SpecialServices specialServices = SpecialServicesMapper.toEntity(dto);
        return specialServicesRepository.save(specialServices);
    }

    @Override
    public List<SpecialServices> findAllByIsDeletedFalse() {
        return specialServicesRepository.findAllByIsDeletedFalse();
    }

    @Override
    public SpecialServices findById(Integer id) {
        return specialServicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Special Services not found with id: " + id));
    }

    @Override
    public SpecialServices findByIdAndIsDeletedFalse(Integer id) {
        return specialServicesRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Special Services not found with id: " + id));
    }

    @Override
    public SpecialServices update(Integer id, SpecialServicesRequestDto dto) {
        SpecialServices specialServicesExisting = findByIdAndIsDeletedFalse(id);
        specialServicesExisting.setServiceName(dto.getServiceName());
        specialServicesExisting.setDescription(dto.getDescription());
        specialServicesExisting.setActive(dto.getActive());

        // Manually set update fields
        specialServicesExisting.setUpdatedBy("admin"); // replace with dynamic username if available
        specialServicesExisting.setUpdatedAt(LocalDateTime.now());
        return specialServicesRepository.save(specialServicesExisting);
    }

    @Override
    public void delete(Integer id) {
        SpecialServices existing = findById(id);
        existing.setIsDeleted(true);
        specialServicesRepository.save(existing);
    }
}

