package com.example.v_expo.repository;

import com.example.v_expo.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface SubscriptionRepository extends PagingAndSortingRepository<Subscription, Long>, JpaSpecificationExecutor<Subscription>, JpaRepository<Subscription, Long> {
//    Optional<Subscription> findByTenantId(Long tenantId);
}
