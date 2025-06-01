package org.example.model;

public record BloodPressure(
        Integer systolic,
        Integer diastolic,
        Long timestamp
) {
    public static BloodPressure defaultBloodPressure() {
        return new BloodPressure(0, 0, 0L);
    }
}
