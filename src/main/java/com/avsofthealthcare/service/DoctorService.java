package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.DoctorRegisterRequest;
import com.avsofthealthcare.dto.DoctorResponseDto;
import com.avsofthealthcare.dto.PatientRegisterRequest;
import com.avsofthealthcare.dto.PatientResponseDto;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface DoctorService {
	String register(DoctorRegisterRequest request, MultipartFile photo) throws IOException;

	DoctorResponseDto update(Long id, DoctorRegisterRequest request, MultipartFile photo) throws IOException;

	DoctorResponseDto getById(Long id);

	List<DoctorResponseDto> getAllActive();

	void softDelete(Long id);
}