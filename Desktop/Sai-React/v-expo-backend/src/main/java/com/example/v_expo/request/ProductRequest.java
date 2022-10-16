package com.example.v_expo.request;


import lombok.Data;


@Data
public class ProductRequest {
    private String name;
    private String description;
    private Long offer_price;
	private Long original_price;
    private Long tenantId;
}
