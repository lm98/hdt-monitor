// src/MqttTemperatureChart.js
import React, { useState, useEffect } from "react";
import mqtt from "mqtt";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  Legend,
} from "recharts";

const MQTT_TOPIC = "state/blood-pressure"

const MqttTemperatureChart = () => {
  // Initialize an empty array for the chart data
  const [data, setData] = useState([]);

  useEffect(() => {
    // Connect to the MQTT broker
    const client = mqtt.connect("ws://localhost:9001");

    client.on("connect", () => {
      console.log("Connected to MQTT broker");
      // Subscribe to the sensor/temperature topic
      client.subscribe(MQTT_TOPIC, (err) => {
        if (err) {
          console.error("Subscription error:", err);
        } else {
          console.log("Subscribed to " + MQTT_TOPIC + " topic");
        }
      });
    });

    // When a message is received, update the chart data
    client.on("message", (topic, message) => {
      if (topic === "state/blood-pressure") {
        try {
          const parsed = JSON.parse(message.toString());
          const { systolic, diastolic } = parsed;

          const newDataPoint = {
            time: new Date().toLocaleTimeString(), // or use ISO for accuracy
            systolic,
            diastolic,
          };

          setData((prev) => [...prev.slice(-49), newDataPoint]); // keep last 50 points
        } catch (err) {
          console.error("Invalid JSON", err);
        }
      }
    });

    // Cleanup: disconnect from the broker when the component unmounts
    return () => {
      client.end();
    };
  }, []);

  return (
    <div style={{ width: "100%", height: 300 }}>
      <LineChart width={800} height={300} data={data}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="time" />
        <YAxis />
        <Tooltip />
        <Legend />
        <Line type="monotone" dataKey="systolic" stroke="#ff7300" />
        <Line type="monotone" dataKey="diastolic" stroke="#387908" />
      </LineChart>
    </div>
  );
};

export default MqttTemperatureChart;
