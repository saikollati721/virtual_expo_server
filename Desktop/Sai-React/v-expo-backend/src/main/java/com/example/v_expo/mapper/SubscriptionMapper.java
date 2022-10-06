package com.example.v_expo.mapper;

import com.example.v_expo.dto.SubscriptionDTO;
import com.example.v_expo.dto.TenantDTO;
import com.example.v_expo.model.Subscription;
import com.example.v_expo.model.Tenant;
import com.example.v_expo.request.SubscriptionRequest;
import com.example.v_expo.request.TenantRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",  unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubscriptionMapper {
    Subscription map(SubscriptionRequest request);
    SubscriptionDTO mapDTO(Subscription subscription);
}
