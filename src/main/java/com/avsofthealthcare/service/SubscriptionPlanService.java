
package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.SubscriptionPlanResponseDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SubscriptionPlanService {

    public List<SubscriptionPlanResponseDTO> getAllPlans() {
        return Arrays.asList(
                new SubscriptionPlanResponseDTO(1L, "Basic", "Basic plan with limited features", 299,
                        Arrays.asList("Basic health card", "QR code access", "Emergency contacts", "Basic medical history")),

                new SubscriptionPlanResponseDTO(2L, "Silver", "Silver plan with additional features", 599,
                        Arrays.asList("Enhanced health card design", "Priority medical support", "Detailed health analytics", "Family member cards")),

                new SubscriptionPlanResponseDTO(3L, "Gold", "Gold plan with premium features", 999,
                        Arrays.asList("Premium gold card design", "24/7 health concierge", "Advanced health monitoring", "Specialist consultations")),

                new SubscriptionPlanResponseDTO(4L, "Platinum", "Platinum plan with all features", 1499,
                        Arrays.asList("Exclusive platinum card", "Personal health manager", "AI-powered health insights", "Global medical coverage"))
        );
    }
}
