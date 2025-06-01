package org.example.util.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.OxygenSaturation;

public class OxygenSaturationStub extends Stub<OxygenSaturation> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public OxygenSaturationStub() {
        super(
                t -> {
                    try {
                        return mapper.writeValueAsString(t);
                    } catch (Exception e) {
                        return OxygenSaturation.defaultOxygenSaturation().toString();
                    }
                },
                s -> {
                    try {
                        return mapper.readValue(s, OxygenSaturation.class);
                    } catch (Exception e) {
                        return OxygenSaturation.defaultOxygenSaturation();
                    }
                }
        );
    }
}
