package com.avsofthealthcare.mapper;

import com.avsofthealthcare.dto.LabRegisterRequest;
import com.avsofthealthcare.entity.LabDetails;
import org.springframework.stereotype.Component;

@Component
public class LabMapper {

    public LabDetails toEntity(LabRegisterRequest dto, Long userId, String certificatesPath) {
        LabDetails entity = new LabDetails();
        entity.setUserId(userId);
        entity.setUserrole("LAB");

        entity.setCenterType(dto.getCenterType());
        entity.setCenterName(dto.getCenterName());
        entity.setOwnerFullName(dto.getOwnerFullName());
        entity.setAvailableTest(dto.getAvailableTest());
        entity.setLabregistrationNumber(dto.getLabregistrationNumber());
        entity.setScanServices(dto.getScanServices());
        entity.setSpecialServices(dto.getSpecialServices());
        entity.setLicenseNumber(dto.getLicenseNumber());
        entity.setLablocation(dto.getLablocation());
        entity.setLabgstNumber(dto.getLabgstNumber());
        entity.setCertificateTypes(dto.getCertificateTypes());
        entity.setCertificates(certificatesPath);
        entity.setAgreeDeclaration(dto.isAgreeDeclaration());
        entity.setPassword(dto.getPassword());
        entity.setConfirmPassword(dto.getConfirmPassword());

        return entity;
    }
}