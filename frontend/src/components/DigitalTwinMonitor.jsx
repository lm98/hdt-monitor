import MqttPropertyLineChart from './MqttPropertyLineChart';

export default function DigitalTwinMonitor() {
  const topicBp = process.env.REACT_APP_TOPIC_BLOOD_PRESSURE || 'sensor/blood-pressure';
  const topicHr = process.env.REACT_APP_TOPIC_HEART_RATE || 'sensor/heart-rate';
  const broker = process.env.REACT_APP_MQTT_BROKER || 'ws://localhost:9001';

  return (
    <div style={{
      fontFamily: "'Inter', sans-serif",
      padding: '2rem',
      maxWidth: '1000px',
      margin: '0 auto'
    }}>
      <h1 style={{
        textAlign: 'center',
        fontSize: '2.5rem',
        fontWeight: 'bold',
        color: '#2c3e50',
        marginBottom: '3rem'
      }}>
        Human Digital Twin Monitor
      </h1>

      {/* Section: Blood Pressure */}
      <section>
        <MqttPropertyLineChart
          broker = {broker}
          topic = {topicBp}
          title="Blood Pressure"
          dataKeys={['systolic', 'diastolic']}
          yDomain={[60, 180]}
        />
        <MqttPropertyLineChart
          broker={broker}
          topic={topicHr}
          title="Average Heart Rate"
          dataKeys={['hrAVG']}
          yDomain={[40, 180]}
        />
      </section>

      {/* Placeholder for future charts */}
      {/* <section>
        <VitalSignChart
          topic="sensor/heartRate"
          title="Heart Rate"
          dataKeys={['bpm']}
          yDomain={[40, 160]}
        />
      </section> */}

    </div>
  );
}
