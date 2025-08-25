package com.avsofthealthcare.entity.dashboard.patientdashboard;

import com.avsofthealthcare.entity.master.HealthConditions;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "family_member_health_condition")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyMemberHealthCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_member_id", nullable = false)
    private FamilyMemberDetails familyMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_condition_id", nullable = false)
    private HealthConditions healthCondition;
}
