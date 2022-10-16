package com.example.v_expo.controller;

import com.example.v_expo.dto.ProductDTO;
import com.example.v_expo.dto.SubscriptionDTO;
import com.example.v_expo.dto.TenantDTO;
import com.example.v_expo.request.ProductRequest;
import com.example.v_expo.request.SubscriptionRequest;
import com.example.v_expo.request.TenantRequest;
import com.example.v_expo.service.ProductService;
import com.example.v_expo.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController(value = "productController")
@RequestMapping(value = "/product")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
    private final ProductService service;

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<ProductDTO> create(@RequestBody ProductRequest productRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.create(productRequest));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductDTO> find(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.find(id));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductDTO> update(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id,productRequest));
    }

    @GetMapping("/tenant/{id}")
    @ResponseBody
    public ResponseEntity<List<ProductDTO>> findByTenantId(@PathVariable("id") Long tenantId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByTenantId(tenantId));
    }
}

