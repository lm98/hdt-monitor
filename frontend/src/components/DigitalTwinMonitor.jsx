import MqttLogger from './MqttLogger';
import MqttPropertyLineChart from './MqttPropertyLineChart';

export default function DigitalTwinMonitor() {
  const topicBp = process.env.REACT_APP_TOPIC_BLOOD_PRESSURE || 'state/blood-pressure';
  const topicHr = process.env.REACT_APP_TOPIC_HEART_RATE || 'state/heart-rate';
  const topicSteps = process.env.REACT_APP_TOPIC_STEPS || 'state/steps';
  const topicBloodOxygen = process.env.REACT_APP_TOPIC_BLOOD_OXYGEN || 'state/blood-oxygen';
  const topicExercise = process.env.REACT_APP_TOPIC_EXERCISE || 'state/exercise';
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
        <MqttLogger
          broker={broker}
          topics={[topicBp, topicHr, topicExercise, topicSteps, topicBloodOxygen]}
        />
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
          title="Heart Rate"
          dataKeys={['hrAVG']}
          yDomain={[40, 180]}
        />
        <MqttPropertyLineChart
          broker={broker}
          topic={topicBloodOxygen}
          title="Oxygen Saturation"
          dataKeys={['percentage']}
          yDomain={[0, 100]}
        />
      </section>

      {/* Placeholder for future charts */}
      {/* <section>
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
          title="Heart Rate"
          dataKeys={['hrAVG']}
          yDomain={[40, 180]}
        />
      </section> */}

    </div>
  );
}
