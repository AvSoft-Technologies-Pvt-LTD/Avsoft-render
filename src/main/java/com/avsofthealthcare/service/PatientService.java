package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.PatientRegisterRequest;
import com.avsofthealthcare.dto.PatientResponseDto;
import com.avsofthealthcare.entity.PatientDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PatientService {

    String register(PatientRegisterRequest request, MultipartFile photo) throws IOException;

    PatientResponseDto  update(Long id, PatientRegisterRequest request, MultipartFile photo) throws IOException;;

    PatientResponseDto getById(Long id);

    List<PatientResponseDto> getAllActive();

    void softDelete(Long id);
}

