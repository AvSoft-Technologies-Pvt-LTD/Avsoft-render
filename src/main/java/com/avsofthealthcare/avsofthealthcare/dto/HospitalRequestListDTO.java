package com.avsofthealthcare.avsofthealthcare.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "hospital_list")
public class HospitalRequestListDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hospitalName;
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String ppnNonPpn;
}
