package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.HospitalListDropdownDto;
import com.avsofthealthcare.dto.HospitalRequestListDTO;

import java.util.List;

public interface HospitalListService {
    List<HospitalRequestListDTO> getAllHospitals();
    HospitalRequestListDTO getHospitalById(Long id);
    HospitalRequestListDTO saveHospital(HospitalRequestListDTO hospitalRequestListDTO);
    void deleteHospital(Long id);
    List<HospitalListDropdownDto> getHospitalDropdown();
}
