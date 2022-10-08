package com.example.v_expo.controller;

import com.example.v_expo.config.TokenProvider;
import com.example.v_expo.mapper.TenantMapper;
import com.example.v_expo.model.Tenant;
import com.example.v_expo.request.LoginRequest;
import com.example.v_expo.service.TenantService;
import com.example.v_expo.dto.TenantDTO;
import com.example.v_expo.request.TenantRequest;
import com.example.v_expo.specification.TenantSpecificationBuilder;
import com.example.v_expo.vo.AuthToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController(value = "tenantController")
@RequestMapping(value = "/tenant")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TenantController {
    private final TenantService tenantService;
    private final TenantMapper tenantMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider jwtTokenUtil;


    @RequestMapping(method = RequestMethod.GET, path = "")
    public Page<TenantDTO> index(TenantSpecificationBuilder builder, @PageableDefault(value = 25, page = 0) Pageable pageable) {
        log.debug("GET /tenants");
        Specification<Tenant> spec = builder.build();
        Page<Tenant> tenants = tenantService.findAll(spec, pageable);
        List<TenantDTO> testCaseDTOS = tenantMapper.mapDTOs(tenants.getContent());
        return new PageImpl<>(testCaseDTOS, pageable, tenants.getTotalElements());
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<TenantDTO> create(@RequestBody TenantRequest tenantRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(tenantService.create(tenantRequest));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<TenantDTO> find(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(tenantService.find(id));
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public AuthToken generateToken(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword(),
                        new ArrayList<>()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        AuthToken authToken = new AuthToken(token);
        return authToken;
    }
}

