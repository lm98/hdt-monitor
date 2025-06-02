package org.example.model;
public record Mood(
        String mood,
        Double energyLevel,
        Long timestamp
) {
    public static Mood defaultMood() {
        return new Mood("Normal", -1.0, -1L);
    }
}
