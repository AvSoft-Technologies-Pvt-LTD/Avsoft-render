package com.avsofthealthcare.entity.dashboard.doctordashboard;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.entity.User;
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;  // ✅ Link StaffDetails → User

	private String fullName;
	private String emailId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;


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

	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<StaffPermission> staffPermissions = new HashSet<>();

	// ✅ equals & hashCode only on id
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof StaffDetails)) return false;
		StaffDetails that = (StaffDetails) o;
		return id != null && id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

