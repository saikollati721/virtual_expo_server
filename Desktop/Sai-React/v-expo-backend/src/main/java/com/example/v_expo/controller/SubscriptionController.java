package com.example.v_expo.controller;

import com.example.v_expo.dto.SubscriptionDTO;
import com.example.v_expo.dto.TenantDTO;
import com.example.v_expo.mapper.SubscriptionMapper;
import com.example.v_expo.request.SubscriptionRequest;
import com.example.v_expo.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController(value = "subscriptionController")
@RequestMapping
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubscriptionController {
    private final SubscriptionService service;

    @PostMapping("/subscription")
    @ResponseBody
    public ResponseEntity<SubscriptionDTO> create(@RequestBody SubscriptionRequest subscriptionRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(subscriptionRequest));
    }

    @GetMapping("/subscription/{id}")
    @ResponseBody
    public ResponseEntity<SubscriptionDTO> find(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.find(id));
    }

    @GetMapping("/subscription/tenant/{id}")
    @ResponseBody
    public ResponseEntity<SubscriptionDTO> findByTenantId(@PathVariable("id") Long tenantId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByTenantId(tenantId));
    }
}

