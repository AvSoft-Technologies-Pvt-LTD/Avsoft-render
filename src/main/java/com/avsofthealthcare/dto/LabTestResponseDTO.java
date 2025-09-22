package com.avsofthealthcare.dto;

import lombok.Data;

@Data
public class LabTestResponseDTO {
    private Long id;
//    private String code;
    private String testname;
    private String testType;
    private String description;
    private double price;
    private String labName;
}
