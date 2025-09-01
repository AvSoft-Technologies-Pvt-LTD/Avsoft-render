package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.HospitalListDropdownDto;
import com.avsofthealthcare.entity.HospitalList;
import com.avsofthealthcare.service.HospitalListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/hospitals")
public class HospitalListController {

    @Autowired
private HospitalListService hospitalListService;

    @GetMapping
public List<HospitalList> getAllHospitals(){
    return  hospitalListService.getAllHospitals();
}

    @GetMapping("/{id}")
    public HospitalList getHospitalById(@PathVariable Long id){
        return  hospitalListService.getHospitalById(id);
    }

    @PostMapping
    public HospitalList saveHospital(@Valid @RequestBody HospitalList hospitalList){
        return hospitalListService.saveHospital(hospitalList);
    }

    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable Long id){
        hospitalListService.deleteHospital(id);
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<HospitalListDropdownDto>> getHospitalDropdown() {
        return ResponseEntity.ok(hospitalListService.getHospitalDropdown());
    }


}
