package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.HospitalListDropdownDto;
import com.avsofthealthcare.entity.HospitalList;

import java.util.List;

public interface HospitalListService {
	List<HospitalList> getAllHospitals();
	HospitalList getHospitalById(Long id);
	HospitalList saveHospital(HospitalList hospitalList);
	void deleteHospital(Long id);
	List<HospitalListDropdownDto> getHospitalDropdown();
}