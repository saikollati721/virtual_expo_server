package com.example.v_expo.mapper;

import com.example.v_expo.dto.ProductDTO;
import com.example.v_expo.dto.SubscriptionDTO;
import com.example.v_expo.model.Product;
import com.example.v_expo.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Product map(ProductRequest request);

    ProductDTO mapDTO(Product product);

    List<ProductDTO> mapDTOs(List<Product> productList);
}
