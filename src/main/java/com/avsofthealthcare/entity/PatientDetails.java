package com.avsofthealthcare.entity;

import com.avsofthealthcare.entity.master.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patient_details")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDetails {

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
    private String aadhaar;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")  // foreign key column in patient_details table
    private Gender gender;


    @Column(name = "patient_dob")
    private LocalDate dob;

    private String email;
    private String occupation;

//    @Column(columnDefinition = "TEXT")
//    private String temporaryAddress;
//
//    @Column(columnDefinition = "TEXT")
//    private String permanentAddress;


    // New address fields
    @Column(length = 10)
    private String pincode;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String district;

    @Column(length = 100)
    private String state;




    private String photo;

//    private boolean isSameAsPermanent;

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

	@LastModifiedBy
	@Column(name = "updated_by")
	private String updatedBy;


	@CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}