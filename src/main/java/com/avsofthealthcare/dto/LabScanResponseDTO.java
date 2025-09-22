package com.avsofthealthcare.dto;

import lombok.Data;

@Data
public class LabScanResponseDTO {
    private Long id;
//    private String code;
    private String scanname;
    private String scanType;
    private String description;
    private double price;
    private String labName;
}
