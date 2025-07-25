package com.avsofthealthcare.avsofthealthcare.repository;

import com.avsofthealthcare.avsofthealthcare.entity.ChemicalTherapeuticInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemicalInfoRepository extends JpaRepository<ChemicalTherapeuticInfo, Integer> {}
