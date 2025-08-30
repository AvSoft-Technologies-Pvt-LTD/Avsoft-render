package com.avsofthealthcare.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Table(name = "hospital_list")
public class HospitalRequestListDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = " hospitalName is required")
    private String hospitalName;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "city is required")
    @Pattern(regexp = "^[A-za-z ]+$",message = "city must include only letters")
    private String city;
    @NotBlank(message = "state is required")
    @Pattern(regexp = "^[A-za-z ]+$",message = "state must include only letters")
    private String state;
    @NotBlank(message = "pincode is required")
    @Pattern(regexp = "^[1-9][0-9]{5}$",message = "picode must be a valid 6 digit number")
    private String pinCode;

    private String ppnNonPpn;
}
