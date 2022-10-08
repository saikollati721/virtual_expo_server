package com.example.v_expo.service;

import com.example.v_expo.dto.ProductDTO;
import com.example.v_expo.mapper.ProductMapper;
import com.example.v_expo.model.Product;
import com.example.v_expo.repository.ProductRepository;
import com.example.v_expo.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired, @Lazy}))
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductDTO create(ProductRequest productRequest) {
        Product product = mapper.map(productRequest);
        product = repository.save(product);
        return mapper.mapDTO(product);
    }

    public ProductDTO find(Long id) throws Exception {
        Product product = this.repository.findById(id).orElseThrow(() -> new Exception("Product not found with" + id));
        ProductDTO productDTO = mapper.mapDTO(product);
        return productDTO;
    }

    public List<ProductDTO> findByTenantId(Long tenantId) throws Exception {
        List<Product> productList = this.repository.findByTenantId(tenantId);
        List<ProductDTO> productDTOList = mapper.mapDTOs(productList);
        return productDTOList;
    }
}
