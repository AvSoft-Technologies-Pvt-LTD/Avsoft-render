package com.avsofthealthcare.controller;

import com.avsofthealthcare.entity.SubscriptionPlan;
import com.avsofthealthcare.service.SubscriptionPlanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subscription-plans")
public class SubscriptionPlanController {

    private final SubscriptionPlanService service;

    public SubscriptionPlanController(SubscriptionPlanService service) {
        this.service = service;
    }

    @GetMapping
    public List<SubscriptionPlan> getAllPlans() {
        return service.getAllPlans();
    }
}
