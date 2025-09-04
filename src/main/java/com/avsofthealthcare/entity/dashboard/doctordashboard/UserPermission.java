package com.avsofthealthcare.entity.dashboard.doctordashboard;

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

	// Link to staff
	@ManyToOne
	@JoinColumn(name = "staff_id", nullable = false)
	private StaffDetails staff;

	// Module or form name
	@Column(name = "form_name", nullable = false)
	private String formName;

	// Permissions
	@Column(name = "can_add", nullable = false)
	private Boolean canAdd;

	@Column(name = "can_edit", nullable = false)
	private Boolean canEdit;

	@Column(name = "can_view", nullable = false)
	private Boolean canView;
}