package com.avsofthealthcare.entity.dashboard.patientdashboard;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "patient_vitals")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientVitals {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "patient_id", nullable = false)
	private String patientId;

	@Column(name = "heart_rate")
	private Integer heartRate;

	@Column(name = "temperature")
	private Double temperature;

	@Column(name = "blood_sugar")
	private Double bloodSugar;

	@Column(name = "blood_pressure")
	private String bloodPressure;  // e.g. "120/80"

	@Column(name = "respiratory_rate")
	private Integer respiratoryRate;

	@Column(name = "spo2")
	private Integer spo2;

	@Column(name = "steps")
	private Integer steps;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private String createdBy;

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;


	@Column(name = "updated_by")
	private String updatedBy;


	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
