package com.avsofthealthcare.validation;

import com.avsofthealthcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final UserRepository userRepository;

    public void validateUniqueEmailAndPhone(String email, String phone) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email is already registered");
        }
        if (userRepository.existsByPhone(phone)) {
            throw new IllegalArgumentException("Phone number is already registered");
        }
    }

    // For update
    public void validateUniqueEmailAndPhoneForUpdate(Long userId, String email, String phone) {
        if (userRepository.existsByEmailAndIdNot(email, userId)) {
            throw new IllegalArgumentException("Email is already registered by another user");
        }
        if (userRepository.existsByPhoneAndIdNot(phone, userId)) {
            throw new IllegalArgumentException("Phone number is already registered by another user");
        }
    }
}
