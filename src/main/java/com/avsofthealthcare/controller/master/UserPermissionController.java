package com.avsofthealthcare.controller.master;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avsofthealthcare.dto.master.UserPermissionDto;
import com.avsofthealthcare.service.master.UserPermissionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user-permissions")
@RequiredArgsConstructor
public class UserPermissionController {
	private final UserPermissionService service;

//	@PostMapping
//	public UserPermissionDto assign(@RequestBody UserPermissionDto dto) {
//		return service.assign(dto);
//	}
//
//	@GetMapping("/staff/{staffId}")
//	public List<UserPermissionDto> getByStaff(@PathVariable Long staffId) {
//		return service.getByStaff(staffId);
//	}
//
//	@DeleteMapping("/{id}")
//	public void revoke(@PathVariable Long id) {
//		service.revoke(id);
//	}
}