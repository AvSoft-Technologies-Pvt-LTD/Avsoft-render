package com.avsofthealthcare.avsofthealthcare.controller;

import com.avsofthealthcare.avsofthealthcare.entity.HospitalList;
import com.avsofthealthcare.avsofthealthcare.service.HospitalListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public HospitalList saveHospital(@RequestBody HospitalList hospitalList){
        return hospitalListService.saveHospital(hospitalList);
    }

    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable Long id){
        hospitalListService.deleteHospital(id);
    }


}
