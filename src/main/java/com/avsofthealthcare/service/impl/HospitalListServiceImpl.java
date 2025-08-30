package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.HospitalListDropdownDto;
import com.avsofthealthcare.entity.HospitalList;
import com.avsofthealthcare.mapper.HospitalListMapper;
import com.avsofthealthcare.repository.HospitalListRepository;
import com.avsofthealthcare.service.HospitalListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HospitalListServiceImpl implements HospitalListService {

    @Autowired
    private HospitalListRepository hospitalListRepository;

    @Override
    public List<HospitalList> getAllHospitals() {
        return hospitalListRepository.findAll();
    }

    @Override
    public HospitalList getHospitalById(Long id) {
        return hospitalListRepository.findById(id).orElse(null);
    }

    @Override
    public HospitalList saveHospital(HospitalList hospitalList) {
        return hospitalListRepository.save(hospitalList);
    }

    @Override
    public void deleteHospital(Long id) {
        hospitalListRepository.deleteById(id);
    }

    @Override
    public List<HospitalListDropdownDto> getHospitalDropdown() {
        return hospitalListRepository.findAll().stream()
                .map(HospitalListMapper::toDropdownDto)
                .collect(Collectors.toList());
    }
}
