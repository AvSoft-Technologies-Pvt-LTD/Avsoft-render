package com.avsofthealthcare.avsofthealthcare.mapper;


import com.avsofthealthcare.avsofthealthcare.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUserEntity(String role, String email, String phone, String password, String confirmPassword) {
        User user = new User();
        user.setUserRole(role);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setConfirmPassword(confirmPassword);
        return user;
    }
}