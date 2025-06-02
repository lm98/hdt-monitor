package org.example.model;

public record BloodOxygen(
        Integer id,
        Double percentage,
        Long time,
        Integer time_zone,
        String uid
) {
    public static BloodOxygen defaultBloodOxygen() {
        return new BloodOxygen(
                -1,
                0.0,
                0L,
                0,
                ""
        );
    }
}
