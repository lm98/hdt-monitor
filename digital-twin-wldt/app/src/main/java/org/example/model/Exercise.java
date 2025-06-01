package org.example.model;

import static org.example.model.Exercise.ExerciseType.*;
public record Exercise(
        ExerciseType exerciseType,
        Long startTime,
        Long endTime
) {
    public enum ExerciseType {
        RUNNING,
        BIKING,
        NONE,
    }

    public static Exercise defaultExercise() {
        return new Exercise(NONE, -1L, -1L);
    }
}
