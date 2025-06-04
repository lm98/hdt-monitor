from dotenv import load_dotenv
import os
from llama_index.llms.google_genai import GoogleGenAI
import paho.mqtt.client as mqtt
import json
import re

load_dotenv()

def publish_mqtt(payload: str, mqtt_topic: str, client: mqtt.Client):
    client.publish(mqtt_topic, payload)
    client.disconnect()


def extract_json(text: str):
    # Remove Markdown code fences (```json ... ```)
    cleaned = re.sub(r"```(?:json)?\s*([\s\S]+?)\s*```", r"\1", text.strip())
    return json.loads(cleaned)


def main():
    my_api_key = os.environ.get("GOOGLE_API_KEY")

    llm = GoogleGenAI(
        model="gemini-2.0-flash",
        api_key=my_api_key,  # uses GOOGLE_API_KEY env var by default
    )

    resp_raw = llm.complete("""
                        Analizza i livelli di energia (da 0 a 100) e il mood generale di Leo in base alla seguente chat. 
                        Rispondi solo fornendo un oggetto JSON con tre chiavi: 
                            - 'energyLevel' (valore Double 0.0-100.0)
                            - 'mood' (può essere 'Calm', 'Nervous', 'Stressed', 'Anxious')
                            - 'timestamp' la data dell'ultimo messaggio della chat, in formato numerico

                        Il formato desiderato è:

                        {
                            mood: value
                            energyLevel: value
                            timestamp: value
                        }

                        --Inizio Chat--

                        Leo, [6/2/25 4:49 PM]
                        Pressione 120 77

                        AI 4 HyperTension, [6/2/25 4:52 PM]
                        Perfetto, tutto chiaro! Inserisco subito i tuoi dati. Ricordati di inviarmi una nuova misurazione domani.

                        AI 4 HyperTension, [6/2/25 4:52 PM]
                        La media sulla pressione è 119/76 che rientra nei parametri ottimali di salute. Molto bene!

                        Leo, [6/2/25 4:52 PM]
                        Oggi mi sento un po’ stanco

                        AI 4 HyperTension, [6/2/25 4:53 PM]
                        Mi dispiace sentirti così. Il sonno insufficiente, la mancanza di esercizio fisico e una dieta povera possono contribuire alla stanchezza. Se questo stato persiste o peggiora, ti consiglio di consultare il tuo medico per un approfondimento

                        AI 4 HyperTension, [6/2/25 4:53 PM]
                        La media sulla pressione è 119/76 che rientra nei parametri ottimali di salute. Molto bene!

                        Leo, [6/2/25 4:55 PM]
                        Oggi ho dormito 8 ore ma è abbastanza caldo

                        AI 4 HyperTension, [6/2/25 4:56 PM]
                        Bere molta acqua può aiutare a combattere la stanchezza causata dal calore. Assicurati anche di riposarti in un luogo fresco e tranquillo per favorire il rinfrescamento del tuo corpo
                        """)
    
    resp = extract_json(str(resp_raw))
    print(resp)

    mqtt_broker = os.environ.get("MQTT_BROKER")
    mqtt_port = os.environ.get("MQTT_PORT")
    mqtt_topic = os.environ.get("MQTT_TOPIC")
    client = mqtt.Client()
    client.connect(mqtt_broker, int(mqtt_port), 60)

    publish_mqtt(str(resp), mqtt_topic, client)

if __name__ == "__main__":
    main()