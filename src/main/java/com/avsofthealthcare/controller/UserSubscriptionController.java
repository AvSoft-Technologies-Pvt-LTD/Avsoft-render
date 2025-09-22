package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.UserSubscriptionRequestDTO;
import com.avsofthealthcare.dto.UserSubscriptionResponseDTO;
import com.avsofthealthcare.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/subscription")
@RequiredArgsConstructor
public class UserSubscriptionController {

    private final UserSubscriptionService userSubscriptionService;

    @PostMapping
    public ResponseEntity<UserSubscriptionResponseDTO> activatePlan(
            @PathVariable Long userId,
            @RequestBody UserSubscriptionRequestDTO request) {
        return ResponseEntity.ok(userSubscriptionService.activatePlan(userId, request));
    }

    @GetMapping
    public ResponseEntity<UserSubscriptionResponseDTO> getUserSubscription(@PathVariable Long userId) {
        return ResponseEntity.ok(userSubscriptionService.getUserSubscription(userId));
    }

    // ✅ New PUT endpoint for updating/changing subscription plan
    @PutMapping
    public ResponseEntity<UserSubscriptionResponseDTO> updateSubscription(
            @PathVariable Long userId,
            @RequestBody UserSubscriptionRequestDTO request) {
        return ResponseEntity.ok(userSubscriptionService.updatePlan(userId, request));
    }
}
