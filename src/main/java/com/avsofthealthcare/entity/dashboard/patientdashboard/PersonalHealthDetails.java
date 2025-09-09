package com.avsofthealthcare.entity.dashboard.patientdashboard;

import com.avsofthealthcare.entity.master.BloodGroup;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "personal_health_details")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalHealthDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id", nullable = false)
    private String patientId;

    @Column
    private double height;

    @Column
    private double weight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_group_id")
    private BloodGroup bloodGroup;

    @Column(columnDefinition = "TEXT")
    private String surgeries;

    @Column(columnDefinition = "TEXT")
    private String allergies;

    @Column(name = "is_smoker", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isSmoker = false;

    @Column(name = "years_smoking")
    private Integer yearsSmoking;

    @Column(name = "is_alcoholic", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isAlcoholic = false;

    @Column(name = "years_alcoholic")
    private Integer yearsAlcoholic;

    @Column(name = "is_tobacco", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isTobacco = false;

    @Column(name = "years_tobacco")
    private Integer yearsTobacco;

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
