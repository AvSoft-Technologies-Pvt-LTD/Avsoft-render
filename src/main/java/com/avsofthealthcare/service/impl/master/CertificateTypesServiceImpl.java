package com.avsofthealthcare.service.impl.master;

import com.avsofthealthcare.dto.master.CertificateTypesRequestDto;
import com.avsofthealthcare.entity.master.CertificateTypes;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.CertificateTypesMapper;
import com.avsofthealthcare.repository.master.CertificateTypesRepository;
import com.avsofthealthcare.service.master.CenterTypeService;
import com.avsofthealthcare.service.master.CertificateTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CertificateTypesServiceImpl implements CertificateTypesService {

    @Autowired
    private CertificateTypesRepository certificateTypesRepository;


    @Override
    public CertificateTypes create(CertificateTypesRequestDto dto) {
        CertificateTypes certificateTypes = CertificateTypesMapper.toEntity(dto);
        return certificateTypesRepository.save(certificateTypes);
    }

    @Override
    public List<CertificateTypes> findAllByIsDeletedFalse() {
        return certificateTypesRepository.findAllByIsDeletedFalse();
    }

    @Override
    public CertificateTypes findById(Integer id) {
        return certificateTypesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certifiacte Type not found with id: " + id));
    }

    @Override
    public CertificateTypes findByIdAndIsDeletedFalse(Integer id) {
        return certificateTypesRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certifiacte Type not found with id: " + id));
    }

    @Override
    public CertificateTypes update(Integer id, CertificateTypesRequestDto dto) {
        CertificateTypes certificateTypesExisting = findByIdAndIsDeletedFalse(id);
        certificateTypesExisting.setCertificateName(dto.getCertificateName());
        certificateTypesExisting.setDescription(dto.getDescription());
        certificateTypesExisting.setActive(dto.getActive());

        // Manually set update fields
        certificateTypesExisting.setUpdatedBy("admin"); // replace with dynamic username if available
        certificateTypesExisting.setUpdatedAt(LocalDateTime.now());
        return certificateTypesRepository.save(certificateTypesExisting);
    }

    @Override
    public void delete(Integer id) {
        CertificateTypes existing = findById(id);
        existing.setIsDeleted(true);
        certificateTypesRepository.save(existing);
    }
}
