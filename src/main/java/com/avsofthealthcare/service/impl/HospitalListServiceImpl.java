package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.HospitalListDropdownDto;
import com.avsofthealthcare.dto.HospitalRequestListDTO;
import com.avsofthealthcare.entity.HospitalList;
import com.avsofthealthcare.mapper.HospitalListMapper;
import com.avsofthealthcare.repository.HospitalListRepository;
import com.avsofthealthcare.service.HospitalListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalListServiceImpl implements HospitalListService {

    @Autowired
    private HospitalListRepository repository;

    @Override
    public List<HospitalRequestListDTO> getAllHospitals() {
        return repository.findAll().stream()
                .map(HospitalListMapper::mapToHospitalRequestListDTO)
                .toList();
    }

    @Override
    public HospitalRequestListDTO getHospitalById(Long id) {
        HospitalList hospitalList = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospital not found"));
        return HospitalListMapper.mapToHospitalRequestListDTO(hospitalList);
    }

    @Override
    public HospitalRequestListDTO saveHospital(HospitalRequestListDTO hospitalRequestListDTO) {
        HospitalList hospitalList = HospitalListMapper.mapToHospitalList(hospitalRequestListDTO);
        HospitalList saved = repository.save(hospitalList);
        return HospitalListMapper.mapToHospitalRequestListDTO(saved);
    }

    @Override
    public void deleteHospital(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<HospitalListDropdownDto> getHospitalDropdown() {
        return repository.findAllSortedByNameLettersFirst()
                .stream()
                .map(HospitalListMapper::toDropdownDto)
                .toList();
    }
}
