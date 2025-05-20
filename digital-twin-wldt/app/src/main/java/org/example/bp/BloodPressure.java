package org.example.bp;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public record BloodPressure(Double systolic, Double diastolic) implements Serializable {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static BloodPressure defaultBloodPressure() {
        return new BloodPressure(0.0, 0.0);
    }

    public static BloodPressure fromJsonString(String json) {
        try {
            BloodPressure bp = mapper.readValue(json, BloodPressure.class);
            if (bp.systolic() == null || bp.diastolic() == null) {
                return BloodPressure.defaultBloodPressure();
            }
            return bp;
        } catch (Exception e) {
            return BloodPressure.defaultBloodPressure();
        }
    }
}
