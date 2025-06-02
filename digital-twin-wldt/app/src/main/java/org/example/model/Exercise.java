package org.example.model;

public record Exercise(
        Long start_time,
        String uid,
        Integer start_time_zone,
        Long end_time,
        Integer end_time_zone,
        Integer id,
        String exercise_type
) {
    public static Exercise defaultExercise() {
        return new Exercise(-1L, "", 0, -1L, 0, -1, "None");
    }
}
