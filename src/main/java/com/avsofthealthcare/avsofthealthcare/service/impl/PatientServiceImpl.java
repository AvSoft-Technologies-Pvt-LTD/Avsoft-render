package com.avsofthealthcare.avsofthealthcare.service.impl;

import com.avsofthealthcare.avsofthealthcare.dto.PatientRegisterRequest;
import com.avsofthealthcare.avsofthealthcare.entity.User;
import com.avsofthealthcare.avsofthealthcare.entity.PatientDetails;
import com.avsofthealthcare.avsofthealthcare.mapper.PatientMapper;
import com.avsofthealthcare.avsofthealthcare.mapper.UserMapper;
import com.avsofthealthcare.avsofthealthcare.repository.PatientDetailsRepository;
import com.avsofthealthcare.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.avsofthealthcare.service.PatientService;
import com.avsofthealthcare.avsofthealthcare.util.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired private UserRepository userRepository;
    @Autowired private PatientDetailsRepository patientRepository;
    @Autowired private UserMapper userMapper;
    @Autowired private PatientMapper patientMapper;
    @Autowired private FileStorageService fileStorageService;

    @Override
    public String register(PatientRegisterRequest req) throws IOException {
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            return "Passwords do not match";
        }

        if (userRepository.existsByEmail(req.getEmail()) || userRepository.existsByPhone(req.getPhone())) {
            return "Email or phone already registered";
        }

        User user = userMapper.toUserEntity("PATIENT", req.getEmail(), req.getPhone(), req.getPassword(), req.getConfirmPassword());
        userRepository.save(user);

        String photoPath = req.getPhoto() != null ? fileStorageService.storeFile(req.getPhoto(), "patients") : null;

        PatientDetails details = patientMapper.toEntity(req, user.getId(), photoPath);
        patientRepository.save(details);

        return "Patient registered successfully";
    }
}
