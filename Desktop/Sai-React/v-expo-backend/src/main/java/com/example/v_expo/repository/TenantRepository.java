package com.example.v_expo.repository;

import com.example.v_expo.model.Tenant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TenantRepository extends PagingAndSortingRepository<Tenant, Long>, JpaSpecificationExecutor<Tenant>, JpaRepository<Tenant, Long> {
}
