package org.example.util.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Mood;

public class MoodStub extends Stub<Mood> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public MoodStub() {
        super(
                t -> {
                    try {
                        return mapper.writeValueAsString(t);
                    } catch (Exception e) {
                        return Mood.defaultMood().toString();
                    }
                },
                s -> {
                    try {
                        return mapper.readValue(s, Mood.class);
                    } catch (Exception e) {
                        return Mood.defaultMood();
                    }
                }
        );
    }
}
