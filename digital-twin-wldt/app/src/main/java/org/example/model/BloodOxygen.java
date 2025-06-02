package org.example.model;

public record BloodOxygen(
        Double percentage,
        Long timestamp,
        Integer timezone
) {
    public static BloodOxygen defaultBloodOxygen() {
        return new BloodOxygen(
                0.0,
                0L,
                0
        );
    }
}
