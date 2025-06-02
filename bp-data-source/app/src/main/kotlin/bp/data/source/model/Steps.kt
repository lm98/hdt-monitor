package bp.data.source.model

import bp.data.source.Mapper

data class StepsMeasurement(
    val count: Int? = null,
    val start_time: Long? = null,
    val uid: String? = null,
    val start_time_zone: Int? = null,
    val end_time_zone: Int? = null,
    val end_time: Long? = null,
    val id: Int? = null,
)

data class Steps(
    val count: Int? = null,
    val startTime: Long? = null,
    val endTime: Long? = null,
    val startTimezone: Int? = null,
    val endTimezone: Int? = null,
)

object StepsMapper : Mapper<StepsMeasurement, Steps> {
    override fun map(input: StepsMeasurement, outputClass: Class<Steps>): Steps {
        return Steps(
            count = input.count,
            startTime = input.start_time,
            endTime = input.end_time,
            startTimezone = input.start_time_zone,
            endTimezone = input.end_time_zone
        )
    }
}