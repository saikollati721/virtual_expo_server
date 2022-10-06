package com.example.v_expo.request;

import com.example.v_expo.model.PlanMode;
import com.example.v_expo.model.PlanType;
import lombok.Data;

@Data
public class SubscriptionRequest {
    private PlanType planType;
    private PlanMode planMode;
    private Long tenantId;
    private String paymentId;
    private String paymentMode;
}
