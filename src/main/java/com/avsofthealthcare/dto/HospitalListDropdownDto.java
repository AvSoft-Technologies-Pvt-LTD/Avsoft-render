package com.avsofthealthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HospitalListDropdownDto {
    private Long id;
    private String hospitalName;
}
