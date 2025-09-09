package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.HospitalRegisterRequest;
import com.avsofthealthcare.entity.HospitalDetails;
import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.mapper.HospitalMapper;
import com.avsofthealthcare.mapper.UserMapper;
import com.avsofthealthcare.repository.HospitalDetailsRepository;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.service.HospitalService;
import com.avsofthealthcare.util.FileStorageService;
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

//        User user = userMapper.toUserEntity("HOSPITAL", req.getEmail(), req.getPhone(), req.getPassword(), req.getConfirmPassword());
//        userRepository.save(user);

//        String nabhCertificatePath = req.getNabhCertificate() != null ?
//                fileStorageService.storeFile(req.getNabhCertificate(), "hospitals") : null;

//        HospitalDetails details = hospitalMapper.toEntity(req, user.getId(), nabhCertificatePath);
//        hospitalRepository.save(details);

        return "Hospital registered successfully";
    }
}