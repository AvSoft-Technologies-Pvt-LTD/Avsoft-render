package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.ChemicalTherapeuticInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemicalInfoRepository extends JpaRepository<ChemicalTherapeuticInfo, Integer> {}