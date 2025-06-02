package bp.data.source.model

import bp.data.source.Mapper

data class ExerciseMeasurement(
    val start_time: Long? = null,
    val uid: String? = null,
    val start_time_zone: Int? = null,
    val end_time_zone: Int? = null,
    val end_time: Long? = null,
    val id: Int? = null,
    val exercise_type: String? = null,
)

data class Exercise(
    val startTime: Long? = null,
    val startTimezone: Int? = null,
    val endTimezone: Int? = null,
    val endTime: Long? = null,
    val id: Int? = null,
    val exerciseType: String? = null,
)

object ExerciseMapper : Mapper<ExerciseMeasurement, Exercise> {
    override fun map(input: ExerciseMeasurement, outputClass: Class<Exercise>): Exercise {
        return Exercise(
            startTime = input.start_time,
            startTimezone = input.start_time_zone,
            endTimezone = input.end_time_zone,
            endTime = input.end_time,
            id = input.id,
            exerciseType = input.exercise_type
        )
    }
}