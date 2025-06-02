package org.example.util.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.BloodOxygen;

public class BloodOxygenStub extends Stub<BloodOxygen> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public BloodOxygenStub() {
        super(
                t -> {
                    try {
                        return mapper.writeValueAsString(t);
                    } catch (Exception e) {
                        return BloodOxygen.defaultBloodOxygen().toString();
                    }
                },
                s -> {
                    try {
                        return mapper.readValue(s, BloodOxygen.class);
                    } catch (Exception e) {
                        return BloodOxygen.defaultBloodOxygen();
                    }
                }
        );
    }
}
