package com.avsofthealthcare.controller;

import com.avsofthealthcare.entity.Insurance;
import com.avsofthealthcare.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceRepository insuranceRepository;

    // Create new insurance enrollment
    @PostMapping
    public Insurance createInsurance(@RequestBody Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    // Fetch insurance by mobile number
    @GetMapping
    public List<Insurance> getInsuranceByMobile(@RequestParam String mobile) {
        return insuranceRepository.findByMobileNumber(mobile);
    }

    // Optional: Get all insurance records
    @GetMapping("/all")
    public List<Insurance> getAllInsurance() {
        return insuranceRepository.findAll();
    }

    // Optional: Update insurance
    @PutMapping("/{id}")
    public Insurance updateInsurance(@PathVariable Long id, @RequestBody Insurance insurance) {
        Insurance existing = insuranceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance record not found"));

        existing.setMobileNumber(insurance.getMobileNumber());
        existing.setInsuranceProvider(insurance.getInsuranceProvider());
        existing.setPolicyNumber(insurance.getPolicyNumber());
        existing.setCoverageType(insurance.getCoverageType());
        existing.setStatus(insurance.getStatus());

        return insuranceRepository.save(existing);
    }

    // Optional: Delete insurance
    @DeleteMapping("/{id}")
    public String deleteInsurance(@PathVariable Long id) {
        insuranceRepository.deleteById(id);
        return "Insurance record deleted successfully";
    }
}
