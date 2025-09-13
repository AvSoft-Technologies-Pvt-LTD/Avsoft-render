package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.entity.VitalDetails;
import com.avsofthealthcare.repository.VitalDetailsRepository;
import com.avsofthealthcare.service.VitalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VitalDetailsServiceImpl implements VitalDetailsService {

    @Autowired
    private VitalDetailsRepository vitalDetailsRepository;

    @Override
    public VitalDetails saveVital(VitalDetails vitalDetails) {
        return vitalDetailsRepository.save(vitalDetails);
    }

    @Override
    public List<VitalDetails> getAllVitals() {
        return vitalDetailsRepository.findAll();
    }

    @Override
    public VitalDetails getVitalById(Long id) {
        return vitalDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("VitalDetails not found with id " + id));
    }

    @Override
    public VitalDetails updateVital(Long id, VitalDetails updatedVital) {
        VitalDetails existingVital = getVitalById(id);

        existingVital.setHeartRate(updatedVital.getHeartRate());
        existingVital.setTemperature(updatedVital.getTemperature());
        existingVital.setBloodSugar(updatedVital.getBloodSugar());
        existingVital.setBloodPressure(updatedVital.getBloodPressure());
        existingVital.setRespiratoryRate(updatedVital.getRespiratoryRate());
        existingVital.setSpO2(updatedVital.getSpO2());
        existingVital.setSteps(updatedVital.getSteps());

        return vitalDetailsRepository.save(existingVital);
    }

    @Override
    public void deleteVital(Long id) {
        VitalDetails existingVital = getVitalById(id);
        vitalDetailsRepository.delete(existingVital);
    }
}
