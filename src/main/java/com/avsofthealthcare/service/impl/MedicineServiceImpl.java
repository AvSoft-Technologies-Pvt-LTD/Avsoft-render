package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.FullMedicineInfo;
import com.avsofthealthcare.entity.Composition;
import com.avsofthealthcare.entity.Medicine;
import com.avsofthealthcare.repository.*;
import com.avsofthealthcare.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired private MedicineRepository medicineRepo;
    @Autowired private ChemicalInfoRepository chemicalRepo;
    @Autowired private SideEffectRepository sideEffectRepo;
    @Autowired private UseRepository useRepo;
    @Autowired private SubstituteRepository substituteRepo;
    @Autowired private CompositionRepository compositionRepo;

    @Override
    public List<FullMedicineInfo> getByComposition(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Composition> resultPage = compositionRepo.findByCompositionContainingIgnoreCase(query, pageable);
        List<Composition> comps = resultPage.getContent();

        return comps.stream().map(comp -> {
            Medicine med = comp.getMedicine();  // Assumes @ManyToOne is correctly set up
            if (med == null) return null;

            int medId = med.getId();

            FullMedicineInfo dto = new FullMedicineInfo();
            dto.setName(med.getName());
            dto.setPrice(med.getPrice());
            dto.setDiscontinued(med.isDiscontinued());
            dto.setManufacturerName(med.getManufacturerName());
            dto.setType(med.getType());
            dto.setPackSizeLabel(med.getPackSizeLabel());
            dto.setHabitForming(med.getHabitForming());

            chemicalRepo.findById(medId).ifPresent(info -> {
                dto.setChemicalClass(info.getChemicalClass());
                dto.setTherapeuticClass(info.getTherapeuticClass());
                dto.setActionClass(info.getActionClass());
            });

            dto.setSideEffects(sideEffectRepo.findByMedicineId(medId).stream().map(se -> se.getEffect()).toList());
            dto.setUses(useRepo.findByMedicineId(medId).stream().map(use -> use.getMedicineUse()).toList());
            dto.setSubstitutes(substituteRepo.findByMedicineId(medId).stream().map(sub -> sub.getSubstituteMedicine()).toList());
            dto.setCompositions(compositionRepo.findByMedicineId(medId).stream().map(c -> c.getComposition()).toList());

            return dto;
        }).filter(info -> info != null).toList();
    }


}