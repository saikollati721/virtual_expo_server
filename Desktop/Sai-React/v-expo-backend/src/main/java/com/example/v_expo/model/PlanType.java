package com.example.v_expo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum PlanType {
    FREE(0, "FREE"),
    PREMIUM(1, "PREMIUM"),
    ENTERPRISE(2, "ENTERPRISE");

    private static final Map<Integer, PlanType> map = new HashMap<>();

    static {
        for (PlanType findByType : PlanType.values()) {
            map.put(findByType.id, findByType);
        }
    }

    @Getter
    private final int id;

    @Getter
    private final String name;

    public static PlanType getType(Integer id) {
        return map.get(id);
    }

    public static int getType(String name) {
        switch (name){
            case "PREMIUM":
                return PREMIUM.id;
            case  "ENTERPRISE":
                return ENTERPRISE.id;
            case "FREE":
            default:
                return FREE.id;
        }
    }
}
