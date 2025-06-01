package org.example.model;

import static org.example.model.Mood.MoodDescription.*;
public record Mood(
        Double energyLevel,
        MoodDescription moodDescription
) {
    public enum MoodDescription {
        NONE,
        HAPPY,
        STRESSED,
    }
    public static Mood defaultMood() {
        return new Mood(-1.0, NONE);
    }
}
