package org.example.util.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.HeartRate;

public class HeartRateStub extends Stub<HeartRate> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public HeartRateStub() {
        super(
                t -> {
                    try {
                        return mapper.writeValueAsString(t);
                    } catch (Exception e) {
                        return HeartRate.defaultHeartRate().toString();
                    }
                },
                s -> {
                    try {
                        return mapper.readValue(s, HeartRate.class);
                    } catch (Exception e) {
                        return HeartRate.defaultHeartRate();
                    }
                }
        );
    }
}
