import MqttPropertyLineChart from './MqttPropertyLineChart';

export default function DigitalTwinMonitor() {
  const topic = process.env.REACT_APP_TOPIC_BLOOD_PRESSURE || 'sensor/bloodPressure';
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
          topic = {topic}
          title="Blood Pressure"
          dataKeys={['systolic', 'diastolic']}
          yDomain={[60, 180]}
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
