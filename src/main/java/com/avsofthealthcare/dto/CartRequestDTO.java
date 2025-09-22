package com.avsofthealthcare.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartRequestDTO {
    private Long patientId;          // Patient/User ID
    private List<Long> testIds;        // Selected Lab Test IDs
    private List<Long> scanIds;        // Selected Scan IDs
}
