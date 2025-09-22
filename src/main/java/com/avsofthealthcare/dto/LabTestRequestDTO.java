package com.avsofthealthcare.dto;

import lombok.Data;

@Data
public class LabTestRequestDTO {
//    private String code;        // e.g. CBC001
    private String testname;
    private String testType;
    private String description;
    private double price;
    private String labName;     // HealthPlus Diagnostics, Apollo, etc.
}
