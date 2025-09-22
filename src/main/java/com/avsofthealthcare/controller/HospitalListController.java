package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.HospitalListDropdownDto;
import com.avsofthealthcare.dto.HospitalRequestListDTO;
import com.avsofthealthcare.service.HospitalListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hospitals")
public class HospitalListController {

    @Autowired
    private HospitalListService hospitalListService;

    @GetMapping
    public List<HospitalRequestListDTO> getAllHospitals() {
        return hospitalListService.getAllHospitals();
    }

    @GetMapping("/{id}")
    public HospitalRequestListDTO getHospitalById(@PathVariable Long id) {
        return hospitalListService.getHospitalById(id);
    }

    @PostMapping
    public HospitalRequestListDTO saveHospital(@Valid @RequestBody HospitalRequestListDTO hospitalRequestListDTO) {
        return hospitalListService.saveHospital(hospitalRequestListDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable Long id) {
        hospitalListService.deleteHospital(id);
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<HospitalListDropdownDto>> getHospitalDropdown() {
        return ResponseEntity.ok(hospitalListService.getHospitalDropdown());
    }
}
