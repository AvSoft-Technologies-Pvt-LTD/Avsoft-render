package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.UserSubscriptionRequestDTO;
import com.avsofthealthcare.dto.UserSubscriptionResponseDTO;

public interface UserSubscriptionService {

    UserSubscriptionResponseDTO activatePlan(Long userId, UserSubscriptionRequestDTO request);

    UserSubscriptionResponseDTO getUserSubscription(Long userId);

    // ✅ New method for updating subscription plan
    UserSubscriptionResponseDTO updatePlan(Long userId, UserSubscriptionRequestDTO request);
}
