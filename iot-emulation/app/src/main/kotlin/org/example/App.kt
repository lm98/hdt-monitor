/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.eclipse.paho.client.mqttv3.MqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@Serializable
data class BloodPressure(val systolic: Double, val diastolic: Double)

fun main(): Unit = runBlocking {
    val broker = System.getenv().getOrDefault("MQTT_BROKER", "localhost")
    val port = System.getenv().getOrDefault("MQTT_PORT", "1883")
    val topic = System.getenv().getOrDefault("MQTT_TOPIC", "sensor/blood-pressure")
    val mqttAsyncClient = MqttAsyncClient("tcp://$broker:$port", MqttClient.generateClientId())
    val mqttOptions = MqttConnectOptions().apply { isCleanSession = true }
    mqttAsyncClient.connect(mqttOptions)

    launch {
        emulate(mqttAsyncClient, topic, 5L)
    }
}

suspend fun emulate(mqttAsyncClient: MqttAsyncClient, topic: String, delay: Long) {
    while(true) {
        delay(TimeUnit.SECONDS.toMillis(delay))
        val systolicBloodPressure = Random.nextDouble(110.00, 140.00)
        val diastolicBloodPressure = Random.nextDouble(45.00, 90.00)
        val bp = BloodPressure(systolicBloodPressure, diastolicBloodPressure)
        sendToMqtt(mqttAsyncClient, topic, Json.encodeToString(bp))
    }
}

fun sendToMqtt(mqttAsyncClient: MqttAsyncClient, topic: String, payload: String) {
    val message = MqttMessage(payload.toByteArray()).apply {
        qos = 1
        isRetained = false
    }
    mqttAsyncClient.publish(topic, message)
    println("Published to $topic: $payload")
}