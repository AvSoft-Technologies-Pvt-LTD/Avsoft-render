package com.avsofthealthcare.avsofthealthcare.service.impl;

import com.avsofthealthcare.avsofthealthcare.entity.User;
import com.avsofthealthcare.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.avsofthealthcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String identifier, String password) {
        User user = identifier.contains("@") ?
                userRepository.findByEmail(identifier).orElse(null) :
                userRepository.findByPhone(identifier).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return "Login successful for " + user.getUserRole();
        }
        return "Invalid credentials";
    }
}
