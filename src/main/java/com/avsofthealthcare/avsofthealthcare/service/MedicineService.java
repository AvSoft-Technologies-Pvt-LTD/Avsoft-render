package com.avsofthealthcare.avsofthealthcare.service;

import com.avsofthealthcare.avsofthealthcare.dto.FullMedicineInfo;
import com.avsofthealthcare.avsofthealthcare.entity.Medicine;
import java.util.List;

public interface MedicineService {
    List<FullMedicineInfo> getByComposition(String query, int page, int size);
}
