package com.example.v_expo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class TokenResponse {
    private String userName;
    private Long tenantId;
    private String token;

    public TokenResponse(String userName, Long tenantId, String token) {
        this.userName = userName;
        this.tenantId = tenantId;
        this.token = token;
    }
}
