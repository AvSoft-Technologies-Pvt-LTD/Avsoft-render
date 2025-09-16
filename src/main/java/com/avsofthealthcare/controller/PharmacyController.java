package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.PharmacyRequest;
import com.avsofthealthcare.entity.Pharmacy;
import com.avsofthealthcare.service.PharmacyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacies")
public class PharmacyController {

    private final PharmacyService service;

    public PharmacyController(PharmacyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pharmacy> getAllPharmacies(@RequestParam(required = false) String city) {
        if (city != null && !city.isEmpty()) {
            return service.searchByCity(city);
        }
        return service.getAllPharmacies();
    }

    @GetMapping("/{id}")
    public Pharmacy getPharmacyById(@PathVariable Long id) {
        return service.getPharmacyById(id);
    }

    @PostMapping
    public Pharmacy createPharmacy(@RequestBody PharmacyRequest request) {
        return service.createPharmacy(request);
    }

    @PutMapping("/{id}")
    public Pharmacy updatePharmacy(@PathVariable Long id, @RequestBody PharmacyRequest request) {
        return service.updatePharmacy(id, request);
    }

    @DeleteMapping("/{id}")
    public String deletePharmacy(@PathVariable Long id) {
        service.deletePharmacy(id);
        return "Pharmacy deleted successfully";
    }
}
