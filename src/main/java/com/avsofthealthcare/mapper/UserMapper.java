package com.avsofthealthcare.mapper;

import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

	public User toUserEntity(Role role, String email, String phone, String password, String confirmPassword) {
		User user = new User();
		user.setRole(role);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		user.setConfirmPassword(confirmPassword);
		return user;
	}

}

