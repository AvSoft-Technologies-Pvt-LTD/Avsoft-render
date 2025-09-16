package com.avsofthealthcare.controller;

import com.avsofthealthcare.entity.MedicalRecord;
import com.avsofthealthcare.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    // Create a new record
    @PostMapping
    public MedicalRecord createRecord(@RequestBody MedicalRecord record) {
        return medicalRecordRepository.save(record);
    }

    // Get all records
    @GetMapping
    public List<MedicalRecord> getAllRecords() {
        return medicalRecordRepository.findAll();
    }

    // Get records by type (OPD, IPD, Virtual)
    @GetMapping("/type/{type}")
    public List<MedicalRecord> getRecordsByType(@PathVariable String type) {
        return medicalRecordRepository.findByType(type);
    }

    // Get a record by ID
    @GetMapping("/{id}")
    public MedicalRecord getRecordById(@PathVariable Long id) {
        Optional<MedicalRecord> record = medicalRecordRepository.findById(id);
        return record.orElseThrow(() -> new RuntimeException("Record not found"));
    }

    // Update a record by ID
    @PutMapping("/{id}")
    public MedicalRecord updateRecord(@PathVariable Long id, @RequestBody MedicalRecord updatedRecord) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setHospital(updatedRecord.getHospital());
        record.setType(updatedRecord.getType());
        record.setChiefComplaint(updatedRecord.getChiefComplaint());
        record.setDateOfVisit(updatedRecord.getDateOfVisit());
        record.setDateOfAdmission(updatedRecord.getDateOfAdmission());
        record.setDateOfDischarge(updatedRecord.getDateOfDischarge());
        record.setStatus(updatedRecord.getStatus());

        return medicalRecordRepository.save(record);
    }

    // Delete a record by ID
    @DeleteMapping("/{id}")
    public String deleteRecord(@PathVariable Long id) {
        medicalRecordRepository.deleteById(id);
        return "Record deleted successfully";
    }
}
