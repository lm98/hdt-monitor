package org.example.model;

public record OxygenSaturation(
        Double percentage
) {
    public static OxygenSaturation defaultOxygenSaturation() {
        return new OxygenSaturation(0.0);
    }
}
