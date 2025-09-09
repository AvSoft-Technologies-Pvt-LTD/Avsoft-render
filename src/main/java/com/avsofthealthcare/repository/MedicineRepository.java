package com.avsofthealthcare.repository;


import com.avsofthealthcare.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {}

