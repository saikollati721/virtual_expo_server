package com.example.v_expo.service;

import com.example.v_expo.dto.SubscriptionDTO;
import com.example.v_expo.dto.TenantDTO;
import com.example.v_expo.mapper.SubscriptionMapper;
import com.example.v_expo.mapper.TenantMapper;
import com.example.v_expo.model.PlanMode;
import com.example.v_expo.model.Subscription;
import com.example.v_expo.model.Tenant;
import com.example.v_expo.repository.SubscriptionRepository;
import com.example.v_expo.repository.TenantRepository;
import com.example.v_expo.request.SubscriptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class SubscriptionService {
	private final SubscriptionRepository repository;
	private final SubscriptionMapper mapper;
	private final TenantRepository tenantRepository;

	public SubscriptionDTO create(SubscriptionRequest subscriptionRequest) throws Exception {
		Subscription subscription = mapper.map(subscriptionRequest);
		LocalDate date = LocalDate.now();
		if(subscription.getPlanMode().equals(PlanMode.MONTHLY)){
			date = date.plusMonths(1);
			subscription.setNextBillingDate(new Timestamp(date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()));
		} else if(subscription.getPlanMode().equals(PlanMode.YEARLY)){
			date = date.plusYears(1);
			subscription.setNextBillingDate(new Timestamp(date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()));
		} else{
			date = date.plusDays(15);
			subscription.setNextBillingDate(new Timestamp(date.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()));
		}
		subscription = repository.save(subscription);
		return mapper.mapDTO(subscription);
	}

	public SubscriptionDTO find(Long id) throws Exception {
		Subscription subscription = this.repository.findById(id).orElseThrow(() -> new Exception("Subscription not found with" + id));
		SubscriptionDTO subscriptionDTO = mapper.mapDTO(subscription);
		return subscriptionDTO;
	}

//	public SubscriptionDTO findByTenantId(Long tenantId) throws Exception {
//		Subscription subscription = this.repository.findByTenantId(tenantId).orElseThrow(() -> new Exception("Subscription not found with TenantId" + tenantId));
//		SubscriptionDTO subscriptionDTO = mapper.mapDTO(subscription);
//		return subscriptionDTO;
//	}
}
