package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.PharmacyRequest;
import com.avsofthealthcare.entity.Pharmacy;
import com.avsofthealthcare.repository.PharmacyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyService {

    private final PharmacyRepository repository;

    public PharmacyService(PharmacyRepository repository) {
        this.repository = repository;
    }

    public List<Pharmacy> getAllPharmacies() {
        return repository.findAll();
    }

    public List<Pharmacy> searchByCity(String city) {
        return repository.findByAddressContainingIgnoreCase(city);
    }

    public Pharmacy getPharmacyById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Pharmacy not found"));
    }

    public Pharmacy createPharmacy(PharmacyRequest request) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName(request.getName());
        pharmacy.setAddress(request.getAddress());
        pharmacy.setLatitude(request.getLatitude());
        pharmacy.setLongitude(request.getLongitude());
        pharmacy.setPhone(request.getPhone());
        pharmacy.setHours(request.getHours());
        return repository.save(pharmacy);
    }

    public Pharmacy updatePharmacy(Long id, PharmacyRequest request) {
        Pharmacy pharmacy = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));

        pharmacy.setName(request.getName());
        pharmacy.setAddress(request.getAddress());
        pharmacy.setLatitude(request.getLatitude());
        pharmacy.setLongitude(request.getLongitude());
        pharmacy.setPhone(request.getPhone());
        pharmacy.setHours(request.getHours());

        return repository.save(pharmacy);
    }

    public void deletePharmacy(Long id) {
        repository.deleteById(id);
    }
}
