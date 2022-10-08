package com.example.v_expo.request;

import com.example.v_expo.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class TenantRequest {
    private String userName;
    private String password;
    private String organizationName;
    private String companyEMail;
    private String personalEMail;
    private String place;
    private String contactNumber;
    private String logoUrl;
    private String description;
    private Category category;
}
