package com.avsofthealthcare.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.avsofthealthcare.entity.master.Gender;
import com.avsofthealthcare.entity.master.PracticeType;
import com.avsofthealthcare.entity.master.Specialization;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctor_details")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;


	private String userrole;

	private String firstName;
	private String middleName;
	private String lastName;
	private String phone;
	private String email;
	private String aadhaar;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id")  // foreign key column in doctor_details table
	private Gender gender;


	@Column(name = "doctor_dob")
	private LocalDate dob;

	private String registrationNumber;

	//  @Column(columnDefinition = "TEXT")
	//  private String temporaryAddress;
	//
	//  @Column(columnDefinition = "TEXT")
	//  private String permanentAddress;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "practice_type_id")
	private PracticeType practiceType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "specialization_id")
	private Specialization specialization;

	private String qualification;

	private String photo;


	@Column(length = 10)
	private String pincode;

	@Column(length = 100)
	private String city;

	@Column(length = 100)
	private String district;

	@Column(length = 100)
	private String state;

	//  private boolean isSameAsPermanent;

	private String password;
	private String confirmPassword;

	private boolean agreeDeclaration;


	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
	private Boolean active = true;


	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted = false;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private String createdBy;

	// @LastModifiedBy
	@Column(name = "updated_by")
	private String updatedBy;

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	// @LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

}
