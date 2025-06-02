package org.example.model;

public record Steps(
        Integer count,
        Long start_time,
        String uid,
        Integer start_time_zone,
        Integer end_time_zone,
        Long end_time,
        Integer id
) {
   public static Steps defaultSteps() {
       return new Steps(
               0,
               0L,
               "",
               0,
               0,
               0L,
               -1
       );
   }
}
