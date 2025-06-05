from dotenv import load_dotenv
import os
from llama_index.llms.google_genai import GoogleGenAI
import paho.mqtt.client as mqtt
import json
import re

load_dotenv()

def read_file(path: str) -> str:
    with open(path, "r") as file:
        content = file.read()
        return content

def setup_mqtt() -> mqtt.Client:
    mqtt_broker = os.environ.get("MQTT_BROKER")
    mqtt_port = os.environ.get("MQTT_PORT")
    client = mqtt.Client()
    client.connect(mqtt_broker, int(mqtt_port), 60)
    return client


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

    chat = read_file("./resources/input.txt")
    resp_raw = llm.complete(chat)
    
    resp = extract_json(str(resp_raw))
    print(resp)


    mqtt_topic = os.environ.get("MQTT_TOPIC")

    #client = setup_mqtt()
    #publish_mqtt(str(resp), mqtt_topic, client)

if __name__ == "__main__":
    main()