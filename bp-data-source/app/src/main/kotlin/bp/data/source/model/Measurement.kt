package bp.data.source.model

data class HeartRate(
    val hrAVG: Int? = null,
    val hrEnd: Long? = null,
    val hrMAX: Int? = null,
    val hrMC: Int? = null,
    val hrMIN: Int? = null,
    val hrStart: Long? = null
)

data class Measurement(
    val bodyPosition: Int? = null,
    val diastolic: Int? = null,
    val systolic: Int? = null,
    val id: Int? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val timestamp: Long? = null,
    val timezone: Int? = null,
    val uid: String? = null,
    val heartRate: HeartRate? = null
)
