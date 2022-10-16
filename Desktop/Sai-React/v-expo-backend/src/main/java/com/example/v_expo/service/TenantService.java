package com.example.v_expo.service;

import com.example.v_expo.config.TokenProvider;
import com.example.v_expo.model.TokenResponse;
import com.example.v_expo.dto.TenantDTO;
import com.example.v_expo.mapper.TenantMapper;
import com.example.v_expo.model.Tenant;
import com.example.v_expo.repository.TenantRepository;
import com.example.v_expo.request.LoginRequest;
import com.example.v_expo.request.TenantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "tenantService")
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class TenantService implements UserDetailsService {
    private final TenantRepository tenantRepository;
    private final TenantMapper tenantMapper;
    private final BCryptPasswordEncoder bcryptEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider jwtTokenUtil;

    public TenantDTO create(TenantRequest tenantRequest) throws Exception {
        Tenant tenant = tenantMapper.map(tenantRequest);
        tenant.setPassword(bcryptEncoder.encode(tenantRequest.getPassword()));
        tenant = tenantRepository.save(tenant);
        return tenantMapper.mapDTO(tenant);
    }

    public TenantDTO update(Long id, TenantRequest tenantRequest) throws Exception {
        Tenant tenant = this.tenantRepository.findById(id).orElseThrow(() -> new Exception("Tenant not found with" + id));
        tenant = tenantMapper.merge(tenant, tenantRequest);
        tenant = tenantRepository.save(tenant);
        return tenantMapper.mapDTO(tenant);
    }


    public TenantDTO find(Long id) throws Exception {
        Tenant tenant = this.tenantRepository.findById(id).orElseThrow(() -> new Exception("Tenant not found with" + id));
        TenantDTO tenantDTO = tenantMapper.mapDTO(tenant);
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

    public TokenResponse generateToken(LoginRequest loginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword(),
                        new ArrayList<>()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        Tenant tenant = tenantRepository.findByUserName(loginRequest.getUserName());
        TokenResponse tokenResponse = new TokenResponse(tenant.getUserName(), tenant.getId(), token);
        return tokenResponse;
    }
}
