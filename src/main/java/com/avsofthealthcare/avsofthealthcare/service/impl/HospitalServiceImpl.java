package com.avsofthealthcare.avsofthealthcare.service.impl;

import com.avsofthealthcare.avsofthealthcare.dto.HospitalRegisterRequest;
import com.avsofthealthcare.avsofthealthcare.entity.HospitalDetails;
import com.avsofthealthcare.avsofthealthcare.entity.User;
import com.avsofthealthcare.avsofthealthcare.mapper.HospitalMapper;
import com.avsofthealthcare.avsofthealthcare.mapper.UserMapper;
import com.avsofthealthcare.avsofthealthcare.repository.HospitalDetailsRepository;
import com.avsofthealthcare.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.avsofthealthcare.service.HospitalService;
import com.avsofthealthcare.avsofthealthcare.util.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired private UserRepository userRepository;
    @Autowired private HospitalDetailsRepository hospitalRepository;
    @Autowired private UserMapper userMapper;
    @Autowired private HospitalMapper hospitalMapper;
    @Autowired private FileStorageService fileStorageService;

    @Override
    public String register(HospitalRegisterRequest req) throws IOException {
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            return "Passwords do not match";
        }

        if (userRepository.existsByEmail(req.getEmail()) || userRepository.existsByPhone(req.getPhone())) {
            return "Email or phone already registered";
        }

        User user = userMapper.toUserEntity("HOSPITAL", req.getEmail(), req.getPhone(), req.getPassword(), req.getConfirmPassword());
        userRepository.save(user);

        String nabhCertificatePath = req.getNabhCertificate() != null ?
                fileStorageService.storeFile(req.getNabhCertificate(), "hospitals") : null;

        HospitalDetails details = hospitalMapper.toEntity(req, user.getId(), nabhCertificatePath);
        hospitalRepository.save(details);

        return "Hospital registered successfully";
    }
}