package com.avsofthealthcare.service;

import com.avsofthealthcare.entity.VitalDetails;

import java.util.List;

public interface VitalDetailsService {
    VitalDetails saveVital(VitalDetails vitalDetails);
    List<VitalDetails> getAllVitals();
    VitalDetails getVitalById(Long id);
    VitalDetails updateVital(Long id, VitalDetails vitalDetails);
    void deleteVital(Long id);
}
