package com.avsofthealthcare.dto.dashboard.doctordashboard;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StaffRequestDTO {
	private String fullName;
	private Long roleId;
	private String emailId;
	private Integer specializationId;
	private String phoneNumber;
	private String password;
	private Integer genderId;
	private String signature;
	private MultipartFile photo;     // file upload
}