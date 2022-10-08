package com.example.v_expo.service;

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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service(value = "tenantService")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class TenantService implements UserDetailsService {
    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;
    private final BCryptPasswordEncoder bcryptEncoder;

    public TenantDTO create(TenantRequest tenantRequest) throws Exception {
        Tenant tenant = tenantMapper.map(tenantRequest);
        tenant.setPassword(bcryptEncoder.encode(tenantRequest.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Tenant tenant = tenantRepository.findByUserName(userName);
        if (tenant == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(tenant.getUserName(), tenant.getPassword(), new ArrayList<>());
        return userDetails;
    }

    private Set<SimpleGrantedAuthority> getAuthority() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }
}
