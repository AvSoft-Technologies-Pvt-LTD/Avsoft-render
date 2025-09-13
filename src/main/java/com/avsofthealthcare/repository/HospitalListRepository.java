package com.avsofthealthcare.repository;

import java.util.List;

import com.avsofthealthcare.entity.HospitalList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HospitalListRepository extends JpaRepository<HospitalList, Long> {

	@Query(value = "SELECT * FROM hospital_list ORDER BY " +
			"CASE WHEN LEFT(hospital_name, 1) ~ '^[0-9]' THEN 1 ELSE 0 END, hospital_name ASC",
			nativeQuery = true)
	List<HospitalList> findAllSortedByNameLettersFirst();
}
