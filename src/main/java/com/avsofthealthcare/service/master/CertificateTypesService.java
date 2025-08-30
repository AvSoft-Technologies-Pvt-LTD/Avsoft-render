package com.avsofthealthcare.service.master;

import com.avsofthealthcare.dto.master.CertificateTypesRequestDto;
import com.avsofthealthcare.entity.master.CertificateTypes;

import java.util.List;

public interface CertificateTypesService {
    CertificateTypes create(CertificateTypesRequestDto dto);
    List<CertificateTypes> findAllByIsDeletedFalse();
    CertificateTypes findById(Integer id);
    CertificateTypes findByIdAndIsDeletedFalse(Integer id);
    CertificateTypes update(Integer id, CertificateTypesRequestDto dto);
    void delete(Integer id);
}