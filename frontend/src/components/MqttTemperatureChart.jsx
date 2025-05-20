// src/MqttTemperatureChart.js
import React, { useState, useEffect } from 'react';
import mqtt from 'mqtt';
import { LineChart, Line, XAxis, YAxis, Tooltip, CartesianGrid, Legend } from 'recharts';

const MqttTemperatureChart = () => {
  // Initialize an empty array for the chart data
  const [data, setData] = useState([]);

  useEffect(() => {
    // Connect to the MQTT broker
    const client = mqtt.connect('ws://localhost:9001');

    client.on('connect', () => {
      console.log('Connected to MQTT broker');
      // Subscribe to the sensor/temperature topic
      client.subscribe('sensor/temperature', (err) => {
        if (err) {
          console.error('Subscription error:', err);
        } else {
          console.log('Subscribed to sensor/temperature topic');
        }
      });
    });

    // When a message is received, update the chart data
    client.on('message', (topic, message) => {
      try {
        // Convert message to string and attempt to parse as JSON.
        // If the message is not JSON, parseFloat will handle a raw number.
        let temperature;
        try {
          const parsed = JSON.parse(message.toString());
          temperature = parsed.temperature || parsed;
        } catch (jsonError) {
          temperature = parseFloat(message.toString());
        }
        
        // Get the current time to label the x-axis
        const currentTime = new Date().toLocaleTimeString();

        // Append the new data point to the state, limiting to the last 10 entries
        setData(prevData => {
          const updatedData = [...prevData, { time: currentTime, temperature }];
          return updatedData.slice(-10);
        });
      } catch (error) {
        console.error('Error processing MQTT message:', error);
      }
    });

    // Cleanup: disconnect from the broker when the component unmounts
    return () => {
      client.end();
    };
  }, []);

  return (
    <div>
      <h2>MQTT Temperature Chart</h2>
      <LineChart width={600} height={300} data={data}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="time" />
        <YAxis label={{ value: 'Temperature (°C)', angle: -90, position: 'insideLeft' }} />
        <Tooltip />
        <Legend />
        <Line type="monotone" dataKey="temperature" stroke="#82ca9d" activeDot={{ r: 8 }} />
      </LineChart>
    </div>
  );
};

export default MqttTemperatureChart;
