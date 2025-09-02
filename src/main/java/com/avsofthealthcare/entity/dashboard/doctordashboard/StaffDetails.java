package com.avsofthealthcare.entity.dashboard.doctordashboard;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.entity.master.Gender;
import com.avsofthealthcare.entity.master.Specialization;

@Entity
@Table(name = "staff_details")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	private String fullName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;

	private String emailId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "specialization_id")
	private Specialization specialization;

	private String phoneNumber;

	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id")
	private Gender gender;

	private String signature;

	private String photo;

	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
	private Boolean active = true;

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted = false;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private String createdBy;


	@Column(name = "updated_by")
	private String updatedBy;

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;


	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
