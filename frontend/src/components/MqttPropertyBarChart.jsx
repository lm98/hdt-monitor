import React, { useEffect, useState } from 'react';
import mqtt from 'mqtt';
import {
  BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, LabelList, ResponsiveContainer,
} from 'recharts';

const typeColors = {
  RUNNING: '#8884d8',
  BIKING: '#82ca9d',
};

function formatTimestamp(ms) {
  const date = new Date(ms);
  return `${date.getHours()}:${String(date.getMinutes()).padStart(2, '0')}`;
}

const MqttPropertyBarChart = ({ broker, topic }) => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const client = mqtt.connect(broker);

    client.on('connect', () => {
      console.log(`Connected to ${broker}`);
      client.subscribe(topic);
    });

    client.on('message', (receivedTopic, message) => {
      if (receivedTopic === topic) {
        try {
          const payload = JSON.parse(message.toString());
          const newEntry = {
            ...payload,
            duration: (payload.endTime - payload.startTime) / 60000, // in minutes
            start: formatTimestamp(payload.startTime),
            end: formatTimestamp(payload.endTime),
          };
          setData(prev => [...prev.slice(-19), newEntry]); // Keep last 20
        } catch (err) {
          console.error('Invalid message format', err);
        }
      }
    });

    return () => client.end();
  }, [broker, topic]);

  return (
    <div style={{ width: '100%', height: 300 }}>
      <ResponsiveContainer>
        <BarChart
          data={data}
          layout="vertical"
          margin={{ top: 20, right: 30, left: 60, bottom: 20 }}
        >
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis type="number" label={{ value: 'Duration (min)', position: 'insideBottomRight', offset: -10 }} />
          <YAxis dataKey="start" type="category" label={{ value: 'Start Time', angle: -90, position: 'insideLeft' }} />
          <Tooltip
            formatter={(value, name, props) => {
              return name === 'duration'
                ? [`${value.toFixed(1)} min`, 'Duration']
                : [value, name];
            }}
            labelFormatter={(label) => `Started at: ${label}`}
          />
          <Bar
            dataKey="duration"
            isAnimationActive={false}
            label={{ position: 'right', formatter: v => `${v.toFixed(1)} min` }}
          >
            <LabelList dataKey="type" position="insideRight" />
            {data.map((entry, index) => (
              <Cell key={`cell-${index}`} fill={typeColors[entry.type] || '#ccc'} />
            ))}
          </Bar>
        </BarChart>
      </ResponsiveContainer>
    </div>
  );
};

export default MqttPropertyBarChart;
