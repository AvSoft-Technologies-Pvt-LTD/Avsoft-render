package com.avsofthealthcare.service;

import com.avsofthealthcare.entity.SubscriptionPlan;
import com.avsofthealthcare.repository.SubscriptionPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPlanService {

    private final SubscriptionPlanRepository repository;

    public SubscriptionPlanService(SubscriptionPlanRepository repository) {
        this.repository = repository;
        initializePlans();
    }

    // Initialize default plans if none exist
    private void initializePlans() {
        if (repository.count() == 0) {
            repository.save(new SubscriptionPlan("Basic", "Basic plan with limited features", 9.99));
            repository.save(new SubscriptionPlan("Silver", "Silver plan with additional features", 19.99));
            repository.save(new SubscriptionPlan("Gold", "Gold plan with premium features", 29.99));
            repository.save(new SubscriptionPlan("Platinum", "Platinum plan with all features", 49.99));
        }
    }

    public List<SubscriptionPlan> getAllPlans() {
        return repository.findAll();
    }
}
