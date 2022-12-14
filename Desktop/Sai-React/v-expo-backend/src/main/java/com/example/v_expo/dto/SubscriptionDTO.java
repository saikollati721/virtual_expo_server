package com.example.v_expo.dto;

import com.example.v_expo.model.PlanMode;
import com.example.v_expo.model.PlanType;
import com.example.v_expo.model.Tenant;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class SubscriptionDTO {
    private PlanType planType;
    private PlanMode planMode;
    private Long tenantId;
    private String paymentId;
    private String paymentMode;
//    private Tenant tenant;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
