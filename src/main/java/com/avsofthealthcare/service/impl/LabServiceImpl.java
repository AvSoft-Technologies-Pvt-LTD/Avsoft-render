package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.LabRegisterRequest;
import com.avsofthealthcare.entity.LabDetails;
import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.mapper.LabMapper;
import com.avsofthealthcare.mapper.UserMapper;
import com.avsofthealthcare.repository.LabDetailsRepository;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.service.LabService;
import com.avsofthealthcare.util.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LabServiceImpl implements LabService {

    @Autowired private UserRepository userRepository;
    @Autowired private LabDetailsRepository labRepository;
    @Autowired private UserMapper userMapper;
    @Autowired private LabMapper labMapper;
    @Autowired private FileStorageService fileStorageService;

    @Override
    public String register(LabRegisterRequest req) throws IOException {
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            return "Passwords do not match";
        }

        if (userRepository.existsByEmail(req.getEmail()) || userRepository.existsByPhone(req.getPhone())) {
            return "Email or phone already registered";
        }

//        User user = userMapper.toUserEntity("LAB", req.getEmail(), req.getPhone(), req.getPassword(), req.getConfirmPassword());
//        userRepository.save(user);

//        String certificatesPath = req.getCertificates() != null ?
//                fileStorageService.storeFile(req.getCertificates(), "labs") : null;
//
//        LabDetails details = labMapper.toEntity(req, user.getId(), certificatesPath);
//        labRepository.save(details);

        return "Lab registered successfully";
    }
}