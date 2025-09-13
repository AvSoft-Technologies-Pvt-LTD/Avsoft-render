package com.avsofthealthcare.entity.dashboard.patientdashboard;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.avsofthealthcare.entity.master.CoverageType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patient_additional_details")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdditionalDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "patient_id", nullable = false)
	private String patientId;

	@Column
	private String insuranceProviderName;

	@Column
	private String policyNum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coverage_type_id")
	private CoverageType coverageType;

	@Column
	private double coverageAmount;

	@Column(name = "is_primary_holder", columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean isPrimaryHolder = false;

	@Column(name = "policy_start_date")
	private LocalDate policyStartDate;

	@Column(name = "policy_end_date")
	private LocalDate policyEndDate;

	@CreatedBy
	@Column(name = "created_by",  updatable = false)
	private String createdBy;

	@CreatedDate
	@Column(name = "created_at",  updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
