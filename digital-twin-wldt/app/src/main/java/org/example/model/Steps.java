package org.example.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public record Steps(
        Integer amount,
        Long startTime,
        Long endTime
) {
   public static Steps defaultSteps() {
       return new Steps(0,0L,0L);
   }
}
