import React, { useEffect } from 'react';
import mqtt from 'mqtt';

const MqttLogger = ({ broker, topics }) => {
  useEffect(() => {
    const client = mqtt.connect(broker);

    client.on('connect', () => {
      console.log(`[MqttLogger] Connected to broker: ${broker}`);
      topics.forEach((topic) => {
        client.subscribe(topic, (err) => {
          if (err) {
            console.error(`[MqttLogger] Failed to subscribe to ${topic}:`, err);
          } else {
            console.log(`[MqttLogger] Subscribed to topic: ${topic}`);
          }
        });
      });
    });

    client.on('message', (topic, message) => {
      console.log(`[MqttLogger] Topic: ${topic} | Message: ${message.toString()}`);
    });

    return () => {
      console.log('[MqttLogger] Disconnecting from broker...');
      client.end();
    };
  }, [broker, topics]);

  return null; // No UI rendered
};

export default MqttLogger;
