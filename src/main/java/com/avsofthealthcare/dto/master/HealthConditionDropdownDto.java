package com.avsofthealthcare.dto.master;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthConditionDropdownDto {
    private Integer id;
    private String healthConditionName;
}
