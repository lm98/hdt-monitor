package org.example.util.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Steps;

public class StepsStub extends Stub<Steps> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public StepsStub() {
        super(
                t -> {
                     try {
                         return mapper.writeValueAsString(t);
                     } catch (JsonProcessingException e) {
                         return Steps.defaultSteps().toString();
                     }
                },
                s -> {
                    try {
                        return mapper.readValue(s, Steps.class);
                    } catch (Exception e) {
                        return Steps.defaultSteps();
                    }
                }
        );
    }
}
