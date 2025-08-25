package com.avsofthealthcare.mapper;


import java.util.Set;

import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

	public User toUserEntity(Set<Role> roles, String email, String phone, String password, String confirmPassword) {
		User user = new User();
		user.setRoles(roles);
		user.setEmail(email);
		user.setPhone(phone);
		user.setPassword(password);
		user.setConfirmPassword(confirmPassword);
		return user;
	}

}

