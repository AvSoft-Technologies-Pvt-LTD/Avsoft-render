package com.avsofthealthcare.dto.dashboard.doctordashboard;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StaffResponseDTO {
	private Long id;
	private String fullName;
	private String designationName;
	private String emailId;
	private String specializationName;
	private String phoneNumber;
	private String genderName;
	private String signaturePath;
	private String photoPath;
	private Boolean active;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String createdBy;
	private String updatedBy;
}
