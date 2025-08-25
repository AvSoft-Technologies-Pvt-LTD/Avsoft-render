package com.avsofthealthcare.service.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.PersonalHealthDetailsRequestDto;
import com.avsofthealthcare.dto.dashboard.patientdashboard.PersonalHealthDetailsResponseDto;
import com.avsofthealthcare.entity.dashboard.patientdashboard.PersonalHealthDetails;
import com.avsofthealthcare.entity.master.SpecialServices;

import java.util.List;


public interface PersonalHealthDetailsService {

    PersonalHealthDetailsResponseDto create(PersonalHealthDetailsRequestDto dto);

//    List<PersonalHealthDetailsResponseDto> findAllByPatientId(String patientId);

    PersonalHealthDetailsResponseDto findByPatientId(String patientId);

    PersonalHealthDetailsResponseDto updateByPatientId(String patientId, PersonalHealthDetailsRequestDto dto);

    void delete(String patientId);
}