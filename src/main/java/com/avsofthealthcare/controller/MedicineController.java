package com.avsofthealthcare.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.avsofthealthcare.dto.FullMedicineInfo;
import com.avsofthealthcare.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@Tag(name = "Medicine API", description = "Search medicines by composition")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/search")
    @Operation(summary = "Get medicines by composition",
            description = "Returns full medicine details for matching composition keyword")
    public List<FullMedicineInfo> searchByComposition(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size
    ) {
        return medicineService.getByComposition(query, page, size);
    }
}
