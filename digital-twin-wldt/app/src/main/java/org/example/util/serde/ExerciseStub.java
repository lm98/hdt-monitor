package org.example.util.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Exercise;

public class ExerciseStub extends Stub<Exercise> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public ExerciseStub() {
        super(
                t -> {
                    try {
                        return mapper.writeValueAsString(t);
                    } catch (Exception e) {
                        return Exercise.defaultExercise().toString();
                    }
                },
                s -> {
                    try {
                        return mapper.readValue(s, Exercise.class);
                    } catch (Exception e) {
                        return Exercise.defaultExercise();
                    }
                }
        );
    }
}
