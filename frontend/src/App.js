// App.jsx
import "./App.css";
import MqttPropertyLineChart from "./components/MqttPropertyLineChart";
import MqttGaugeChart from "./components/MqttGaugeChart";
import MqttLogger from "./components/MqttLogger";

function App() {
  const broker = process.env.REACT_APP_MQTT_BROKER || 'ws://localhost:9001';
  const topicBp = process.env.REACT_APP_TOPIC_BLOOD_PRESSURE || 'state/blood-pressure';
  const topicHr = process.env.REACT_APP_TOPIC_HEART_RATE || 'state/heart-rate';
  const topicMood = process.env.REACT_APP_TOPIC_MOOD || 'state/mood';

  return (
    <div className="container">
      <h1 className="title">Human Digital Twin Monitor</h1>

      <div className="grid-layout">
        {/* Left Column */}
        <div className="left-column">
          <MqttPropertyLineChart
            broker={broker}
            topic={topicBp}
            dataKeys={["systolic", "diastolic"]}
            title="Blood Pressure"
            yDomain={[60, 180]}
          />
          <MqttPropertyLineChart
            broker={broker}
            topic={topicHr}
            dataKeys={["hrAVG"]}
            title="Heart Rate"
            yDomain={[40, 180]}
          />
        </div>

        {/* Right Column */}
        <div className="right-column">
          <MqttGaugeChart
            broker={broker}
            topic={topicMood}
            label="Energy Level"
            dataKey={"energyLevel"}
          />
        </div>
        <MqttLogger
          broker={broker}
          topics={[topicMood, topicBp, topicHr]}
        />
      </div>
    </div>
  );
}

export default App;
