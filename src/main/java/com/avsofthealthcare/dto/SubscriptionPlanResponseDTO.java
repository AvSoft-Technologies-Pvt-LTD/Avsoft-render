
package com.avsofthealthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class SubscriptionPlanResponseDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private List<String> features;
}
