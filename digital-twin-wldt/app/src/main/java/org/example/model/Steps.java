package org.example.model;

public record Steps(
        Integer count,
        Long startTime,
        Long endTime, Integer startTimezone,
        Integer endTimezone
) {
   public static Steps defaultSteps() {
       return new Steps(
               0,
               0L,
               0L, 0,
               0
       );
   }
}
