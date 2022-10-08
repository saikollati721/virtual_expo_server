package com.example.v_expo.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductDTO {
	private String name;
	private String description;
	private Long offer_price;
	private Long original_price;
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
