
package com.avsofthealthcare.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class UserSubscriptionResponseDTO {
    private Long userId;
    private Long planId;
    private String planName;
    private String status;
    private LocalDate startDate;
    private LocalDate expiryDate;
    private List<String> features;
}
