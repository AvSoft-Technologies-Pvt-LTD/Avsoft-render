package com.avsofthealthcare.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartResponseDTO {
    private Long patientId;
    private List<String> tests;
    private List<String> scans;
    private double totalAmount;
}
