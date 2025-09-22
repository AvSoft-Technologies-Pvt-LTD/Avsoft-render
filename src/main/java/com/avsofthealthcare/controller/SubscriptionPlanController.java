
package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.SubscriptionPlanResponseDTO;
import com.avsofthealthcare.service.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class SubscriptionPlanController {

    private final SubscriptionPlanService planService;

    @GetMapping
    public ResponseEntity<List<SubscriptionPlanResponseDTO>> getPlans() {
        return ResponseEntity.ok(planService.getAllPlans());
    }
}
