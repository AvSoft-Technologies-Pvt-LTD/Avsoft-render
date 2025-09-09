package com.avsofthealthcare.entity.master;

import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPermission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "staff_id", nullable = false)
	private StaffDetails staff;

	@ManyToOne
	@JoinColumn(name = "permission_id", nullable = false)
	private Permission permission;

	private Boolean allowed; // true = has this permission, false = explicitly denied
}
