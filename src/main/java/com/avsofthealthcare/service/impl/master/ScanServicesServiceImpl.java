package com.avsofthealthcare.service.impl.master;

import com.avsofthealthcare.dto.master.ScanServicesRequestDto;
import com.avsofthealthcare.entity.master.ScanServices;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.ScanServicesMapper;
import com.avsofthealthcare.repository.master.ScanServicesRepository;
import com.avsofthealthcare.service.master.ScanServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScanServicesServiceImpl implements ScanServicesService {

    @Autowired
    private ScanServicesRepository scanServicesRepository;


    @Override
    public ScanServices create(ScanServicesRequestDto dto) {
        ScanServices scanServices = ScanServicesMapper.toEntity(dto);
        return scanServicesRepository.save(scanServices);
    }

    @Override
    public List<ScanServices> findAllByIsDeletedFalse() {
        return scanServicesRepository.findAllByIsDeletedFalse();
    }

    @Override
    public ScanServices findById(Integer id) {
        return scanServicesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scan Service not found with id: " + id));
    }

    @Override
    public ScanServices findByIdAndIsDeletedFalse(Integer id) {
        return scanServicesRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Scan Service not found with id: " + id));
    }

    @Override
    public ScanServices update(Integer id, ScanServicesRequestDto dto) {
        ScanServices scanServicesExisting = findByIdAndIsDeletedFalse(id);
        scanServicesExisting.setScanName(dto.getScanName());
        scanServicesExisting.setDescription(dto.getDescription());
        scanServicesExisting.setActive(dto.getActive());

        // Manually set update fields
        scanServicesExisting.setUpdatedBy("admin"); // replace with dynamic username if available
        scanServicesExisting.setUpdatedAt(LocalDateTime.now());
        return scanServicesRepository.save(scanServicesExisting);
    }

    @Override
    public void delete(Integer id) {
        ScanServices existing = findById(id);
        existing.setIsDeleted(true);
        scanServicesRepository.save(existing);
    }
}







