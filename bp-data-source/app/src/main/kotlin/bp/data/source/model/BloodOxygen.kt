package bp.data.source.model

import bp.data.source.Mapper

data class BloodOxygenMeasurement(
    val id: Int? = null,
    val percentage: Double? = null,
    val time: Long? = null,
    val time_zone: Int? = null,
    val uid: String? = null,
)

data class BloodOxygen(
    val percentage: Double? = null,
    val timestamp: Long? = null,
    val timezone: Int? = null,
)

object BloodOxygenMapper: Mapper<BloodOxygenMeasurement, BloodOxygen> {
    override fun map(input: BloodOxygenMeasurement, outputClass: Class<BloodOxygen>): BloodOxygen {
        return BloodOxygen(
            percentage = input.percentage,
            timestamp = input.time,
            timezone = input.time_zone
        )
    }
}