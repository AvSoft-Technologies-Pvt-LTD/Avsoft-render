package com.avsofthealthcare.dto;

import com.avsofthealthcare.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    // Getters and setters
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private UserRole role;
    private String photoUrl;
    private String practiceType;
    private String specialization;
    private String centerType;
    private Set<String> hospitalTypes;
    private Set<String> availableTests;
    private Set<String> scanServices;
    private Set<String> specialServices;
    private Set<String> certificateTypes;

}
