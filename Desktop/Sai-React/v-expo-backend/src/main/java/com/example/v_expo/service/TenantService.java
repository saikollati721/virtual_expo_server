package com.example.v_expo.service;

import java.util.Arrays;

import com.example.v_expo.dto.TenantDTO;
import com.example.v_expo.mapper.TenantMapper;
import com.example.v_expo.model.Tenant;
import com.example.v_expo.repository.TenantRepository;
import com.example.v_expo.request.TenantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class TenantService {
	private final TenantRepository tenantRepository;
	private final TenantMapper tenantMapper;

	public TenantDTO create(TenantRequest tenantRequest) throws Exception {
		Tenant tenant = tenantMapper.map(tenantRequest);
		tenant = tenantRepository.save(tenant);
		  return tenantMapper.mapDTO(tenant);
	}

	public TenantDTO find(Long id) throws Exception {
		Tenant order = this.tenantRepository.findById(id).orElseThrow(() -> new Exception("Tenant not found with" + id));
		TenantDTO tenantDTO = tenantMapper.mapDTO(order);
		return tenantDTO;
	}

	public Page<Tenant> findAll(Specification<Tenant> spec, Pageable pageable) {
		return tenantRepository.findAll(spec, pageable);
	}
}
