package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.FullMedicineInfo;
import com.avsofthealthcare.entity.Medicine;
import java.util.List;

public interface MedicineService {
    List<FullMedicineInfo> getByComposition(String query, int page, int size);
}
