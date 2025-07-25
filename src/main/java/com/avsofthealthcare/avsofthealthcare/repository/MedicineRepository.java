package com.avsofthealthcare.avsofthealthcare.repository;


import com.avsofthealthcare.avsofthealthcare.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {}

