package com.avsofthealthcare.dto;

import lombok.Data;

@Data
public class LabScanRequestDTO {
//    private String code;        // e.g. MRI001
    private String scanname;
    private String scanType;
    private String description;
    private double price;
    private String labName;     // Apollo, Fortis, etc.
}
