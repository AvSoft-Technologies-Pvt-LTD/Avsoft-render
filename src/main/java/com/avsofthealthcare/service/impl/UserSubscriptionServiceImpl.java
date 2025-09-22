package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.SubscriptionPlanResponseDTO;
import com.avsofthealthcare.dto.UserSubscriptionRequestDTO;
import com.avsofthealthcare.dto.UserSubscriptionResponseDTO;
import com.avsofthealthcare.entity.UserSubscription;
import com.avsofthealthcare.repository.UserSubscriptionRepository;
import com.avsofthealthcare.service.SubscriptionPlanService;
import com.avsofthealthcare.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

    private final SubscriptionPlanService planService;
    private final UserSubscriptionRepository subscriptionRepository;

    @Override
    public UserSubscriptionResponseDTO activatePlan(Long userId, UserSubscriptionRequestDTO request) {
        SubscriptionPlanResponseDTO selectedPlan = planService.getAllPlans().stream()
                .filter(plan -> plan.getId().equals(request.getPlanId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        UserSubscription subscription = new UserSubscription();
        subscription.setUserId(userId);
        subscription.setPlanId(selectedPlan.getId());
        subscription.setPlanName(selectedPlan.getName());
        subscription.setStatus("ACTIVE");
        subscription.setStartDate(LocalDate.now());
        subscription.setExpiryDate(LocalDate.now().plusMonths(1));
        subscription.setFeatures(
                selectedPlan.getFeatures() != null ? selectedPlan.getFeatures() : new ArrayList<>()
        );

        subscription = subscriptionRepository.save(subscription);

        return toResponseDTO(subscription);
    }

    @Override
    public UserSubscriptionResponseDTO getUserSubscription(Long userId) {
        UserSubscription subscription = subscriptionRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No subscription found for user " + userId));
        return toResponseDTO(subscription);
    }

    @Override
    public UserSubscriptionResponseDTO updatePlan(Long userId, UserSubscriptionRequestDTO request) {
        UserSubscription subscription = subscriptionRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No subscription found for user " + userId));

        SubscriptionPlanResponseDTO selectedPlan = planService.getAllPlans().stream()
                .filter(plan -> plan.getId().equals(request.getPlanId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        subscription.setPlanId(selectedPlan.getId());
        subscription.setPlanName(selectedPlan.getName());
        subscription.setStatus("ACTIVE");
        subscription.setStartDate(LocalDate.now());
        subscription.setExpiryDate(LocalDate.now().plusMonths(1));
        subscription.setFeatures(
                selectedPlan.getFeatures() != null ? selectedPlan.getFeatures() : new ArrayList<>()
        );

        subscription = subscriptionRepository.save(subscription);

        return toResponseDTO(subscription);
    }

    private UserSubscriptionResponseDTO toResponseDTO(UserSubscription subscription) {
        UserSubscriptionResponseDTO dto = new UserSubscriptionResponseDTO();
        dto.setUserId(subscription.getUserId());
        dto.setPlanId(subscription.getPlanId());
        dto.setPlanName(subscription.getPlanName());
        dto.setStatus(subscription.getStatus());
        dto.setStartDate(subscription.getStartDate());
        dto.setExpiryDate(subscription.getExpiryDate());
        dto.setFeatures(
                subscription.getFeatures() != null ? subscription.getFeatures() : new ArrayList<>()
        );
        return dto;
    }
}
