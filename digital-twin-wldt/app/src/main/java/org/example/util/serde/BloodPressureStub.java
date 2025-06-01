package org.example.util.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.BloodPressure;

public class BloodPressureStub extends Stub<BloodPressure> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public BloodPressureStub() {
        super(
                t -> {
                    try {
                        return mapper.writeValueAsString(t);
                    } catch (JsonProcessingException e) {
                        return BloodPressure.defaultBloodPressure().toString();
                    }
                },
                s -> {
                    try {
                        return mapper.readValue(s, BloodPressure.class);
                    } catch (JsonProcessingException e) {
                        return BloodPressure.defaultBloodPressure();
                    }
                }
        );
    }
}
