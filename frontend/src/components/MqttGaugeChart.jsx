import React, { useEffect, useState } from "react";
import mqtt from "mqtt";
import ReactSpeedometer from "react-d3-speedometer";

const MqttGaugeChart = ({ broker, topic, dataKey, label = "Gauge", min = 0, max = 100 }) => {
  const [value, setValue] = useState(min);

  useEffect(() => {
    const client = mqtt.connect(broker);

    client.on("connect", () => {
      console.log(`Connected to MQTT broker: ${broker}`);
      client.subscribe(topic, (err) => {
        if (err) console.error("Subscription error:", err);
      });
    });

    client.on("message", (receivedTopic, message) => {
      if (receivedTopic === topic) {
        try {
          const parsed = JSON.parse(message.toString());
          const val = parsed[dataKey];
          if (!isNaN(val)) {
            setValue(val);
          }
        } catch (e) {
          console.error("Invalid message format:", e);
        }
      }
    });

    return () => {
      client.end();
    };
  }, [broker, topic, dataKey, label]);

  return (
    <div style={{ display: "flex", flexDirection: "column", alignItems: "center" }}>
      <h2 style={{ marginBottom: "1rem" }}>{label}</h2>
      <div style={{ width: "200px", height: "200px" }}>
        <ReactSpeedometer
            value={value}
            minValue={min}
            maxValue={max}
            needleColor="steelblue"
            startColor="lightgreen"
            endColor="red"
            segments={5}
        />
      </div>
    </div>
  );
};

export default MqttGaugeChart;
