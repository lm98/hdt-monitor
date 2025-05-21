package org.example.hdt.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class BloodPressure(val systolic: Double, val diastolic: Double) {
    companion object {
        fun zero() {
            BloodPressure(0.0, 0.0)
        }

        fun encodeToJsonStr(bp: BloodPressure): String {
            return Json.Default.encodeToString(bp)
        }

        fun decodeFromJsonStr(str: String): BloodPressure {
            return Json.Default.decodeFromString<BloodPressure>(str)
        }
    }
}