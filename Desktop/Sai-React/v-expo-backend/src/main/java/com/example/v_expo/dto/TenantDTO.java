package com.example.v_expo.dto;

import com.example.v_expo.model.Category;
import com.example.v_expo.model.Subscription;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class TenantDTO {
    private String organizationName;
    private String companyEMail;
    private String personalEMail;
    private String place;
    private String contactNumber;
    private String logoUrl;
    private String description;
    private Category category;
    private Subscription subscription;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
