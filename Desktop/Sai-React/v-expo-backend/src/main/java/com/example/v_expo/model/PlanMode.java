package com.example.v_expo.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public enum PlanMode {
    MONTHLY(0, "MONTHLY"), YEARLY(1, "YEARLY");

    private static final Map<Integer, PlanMode> map = new HashMap<>();

    static {
        for (PlanMode findByType : PlanMode.values()) {
            map.put(findByType.id, findByType);
        }
    }

    @Getter
    private final int id;

    @Getter
    private final String name;

    public static PlanMode getMode(Integer id) {
        return map.get(id);
    }

    public static int getMode(String name) {
        switch (name) {
            case "YEARLY":
                return YEARLY.id;
            case "MONTHLY":
            default:
                return MONTHLY.id;
        }
    }
}
