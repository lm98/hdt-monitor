package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HeartRate(
        Integer hrAVG,
        Long hrEnd,
        Integer hrMAX,
        Integer hrMC,
        Integer hrMIN,
        Long hrStart,
        Long timestamp
) {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static HeartRate defaultHeartRate() {
        return new HeartRate(-1, 0L, -1, -1, -1, 0L, 0L);
    }

    public static HeartRate fromJsonString(String json) {
        try {
            HeartRate hr = mapper.readValue(json, HeartRate.class);
            if (hr.hrAVG() == null || hr.hrEnd() == null) {
                return defaultHeartRate();
            }
            return hr;
        } catch (Exception e) {
            return defaultHeartRate();
        }
    }

    public static Optional<String> toJsonString(HeartRate hr) {
        try {
            String json = mapper.writeValueAsString(hr);
            return Optional.of(json);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

