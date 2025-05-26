package org.example.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public record BloodPressure(
        Integer systolic,
        Integer diastolic
) {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static BloodPressure defaultBloodPressure() {
        return new BloodPressure(0, 0);
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

    public static Optional<String> toJsonString(BloodPressure bp) {
        try {
            var str = mapper.writeValueAsString(bp);
            return Optional.of(str);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
