package org.example.model;

public record Exercise(
        Long startTime,
        Integer startTimezone,
        Integer endTimezone, Long endTime,
        Integer id,
        String exerciseType
) {
    public static Exercise defaultExercise() {
        return new Exercise(-1L, 0, 0, -1L, -1, "None");
    }
}
