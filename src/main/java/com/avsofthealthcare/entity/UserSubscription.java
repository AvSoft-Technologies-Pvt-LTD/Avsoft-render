package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "user_subscriptions")
@Data
public class UserSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long planId;
    private String planName;
    private String status;
    private LocalDate startDate;
    private LocalDate expiryDate;

    @ElementCollection
    @CollectionTable(name = "user_subscription_features", joinColumns = @JoinColumn(name = "subscription_id"))
    @Column(name = "feature")
    private java.util.List<String> features;
}
