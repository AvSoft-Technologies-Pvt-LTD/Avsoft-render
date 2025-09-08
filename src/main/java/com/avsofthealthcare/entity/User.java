package com.avsofthealthcare.entity;

import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;


    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String password;

    private String confirmPassword;

	private boolean enabled = true;  // default true

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	private StaffDetails staffDetails;


}