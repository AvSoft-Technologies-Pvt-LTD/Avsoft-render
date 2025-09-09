package com.avsofthealthcare.mapper;

import com.avsofthealthcare.dto.HospitalRegisterRequest;
import com.avsofthealthcare.entity.HospitalDetails;
import org.springframework.stereotype.Component;

@Component
public class HospitalMapper {

    public HospitalDetails toEntity(HospitalRegisterRequest dto, Long userId, String nabhCertificatePath) {
        HospitalDetails entity = new HospitalDetails();
        entity.setUserId(userId);
        entity.setUserrole("HOSPITAL");

        entity.setHospitalName(dto.getHospitalName());
        entity.setHeadCeoName(dto.getHeadCeoName());
        entity.setHospitalregistrationNumber(dto.getHospitalregistrationNumber());
        entity.setLocation(dto.getLocation());
        entity.setHospitalType(dto.getHospitalType());
        entity.setHospitalgstNumber(dto.getHospitalgstNumber());
        entity.setNabhCertificate(nabhCertificatePath);
        entity.setInHouseLab(dto.isInHouseLab());
        entity.setLabLicenseNo(dto.getLabLicenseNo());
        entity.setInHousePharmacy(dto.isInHousePharmacy());
        entity.setPharmacyLicenseNo(dto.getPharmacyLicenseNo());
        entity.setAgreeDeclaration(dto.isAgreeDeclaration());
        entity.setPassword(dto.getPassword());
        entity.setConfirmPassword(dto.getConfirmPassword());

        return entity;
    }
}
