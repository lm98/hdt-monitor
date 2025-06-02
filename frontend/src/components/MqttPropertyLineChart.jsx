import React, { useEffect, useState } from 'react';
import mqtt from 'mqtt';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend
} from 'recharts';

export default function MqttPropertyLineChart({ broker, topic, dataKeys, title, yDomain }) {
  const [data, setData] = useState([]);

  useEffect(() => {
    const client = mqtt.connect(broker);

    client.on('connect', () => {
      console.log(`Subscribed to ${topic}`);
      client.subscribe(topic);
    });

    client.on('message', (t, message) => {
      if (t === topic) {
        try {
          const parsed = JSON.parse(message.toString());
          let entry = null
          if (parsed['timestamp'] !== null) {
            entry = { time: new Date(parsed['timestamp'] * 1000).toLocaleTimeString() };
          } else {
            entry = { time: new Date().toLocaleTimeString() };
          }
          dataKeys.forEach(key => {
            entry[key] = parsed[key];
          });

          setData(prev => [...prev.slice(-49), entry]);
        } catch (err) {
          console.error(`Invalid JSON for ${topic}:`, err);
        }
      }
    });

    return () => client.end();
  }, [broker, topic, dataKeys]);

  return (
    <div style={{ marginBottom: '3rem' }}>
      <h3 style={{
        fontSize: '1.4rem',
        marginBottom: '1rem',
        color: '#2c3e50'
      }}>
        {title}
      </h3>
      <LineChart width={800} height={300} data={data}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="time" />
        <YAxis domain={yDomain} />
        <Tooltip />
        <Legend />
        {dataKeys.map((key, idx) => (
          <Line
            key={key}
            type="monotone"
            dataKey={key}
            stroke={['#e74c3c', '#2980b9', '#27ae60', '#8e44ad'][idx % 4]}
          />
        ))}
      </LineChart>
    </div>
  );
}
