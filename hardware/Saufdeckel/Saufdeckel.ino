/*
Basic ESP8266 MQTT WIFI Pattern

*/

#include <NTPClient.h>
#include <WiFiUdp.h>
#include <WiFiServer.h>
#include <WiFiClientSecure.h>
#include <WiFiClient.h>
#include <ESP8266WiFiType.h>
#include <ESP8266WiFiSTA.h>
#include <ESP8266WiFiScan.h>
#include <ESP8266WiFiMulti.h>
#include <ESP8266WiFiGeneric.h>
#include <ESP8266WiFiAP.h>
#include <ESP8266WiFi.h>
#include <ESP8266HTTPUpdateServer.h>
#include <WiFiManager.h>
#include <aJSON.h>
#include <MQTTClient.h>
#include <time.h>

// Own Libraries
#include "MQTT.h"
#include "LED.h"

//Debug Informations
#define DEBUG //uncomment to enable Debug messages on startup

//Pin Definition
//#define SENSORPIN D6

//Network Definition
const char* mqtt_server = "131.159.222.212";
#define mqtt_port 1883
#define ntp_port 32780

//MQTT Definition
const char* clientId = "ESP-TODO123213213213";
const char* topic = "iot/saufdeckel";
//const char* user = "iteratecIoT";
//const char* pass = "IoT$2017";

//the rest of Global Definitions:
WiFiManager wifimanager;
ESP8266WebServer httpServer(80);
ESP8266HTTPUpdateServer httpUpdater;
WiFiClientSecure wifiClient;
MQTTClient mqttClient;
WiFiUDP wifiUDP;
//NTPClient timeClient(wifiUDP, mqtt_server, 3600, 60000);

void setup() {
  pinMode(BUILTIN_LED, OUTPUT);
  digitalWrite(BUILTIN_LED, LOW);
  Serial.begin(115200);

  wifimanager.autoConnect("Saufdeckel");
  httpUpdater.setup(&httpServer);
  httpServer.begin();
  httpServer.on("/", handleHttpRoot);
  mqttClient.begin(mqtt_server, mqtt_port, wifiClient);
  mqttConnect();

  //timeClient.begin(ntp_port);

  digitalWrite(BUILTIN_LED, HIGH);
}

void loop() {
  mqttConnect();
  mqttClient.loop();
  httpServer.handleClient();
  //timeClient.update();

  uint8 propertee = 0;
  //aJsonObject* root = aJson.createObject();
  //aJson.addNumberToObject(root, "propertyName", propertee);
  //aJson.addNumberToObject(root, "timestamp", (int)timeClient.getEpochTime());
  //char* json = aJson.print(root);
 char* json = "Test123";
  
  mqttConnect(); //refresh connection right before puplish
  mqttClient.publish(topic, json);
  delete  json;
  Serial.println(ESP.getFreeHeap());
  //aJson.deleteItem(root);

  delay(100); //to slow down the loop, depends on the code
}


  


void mqttConnect() {
  if (!mqttClient.connected()) {
    while (!mqttClient.connect(clientId)) {
      Serial.print(mqttClient.connected());
      Serial.print(".");
      delay(1000);
    }

    Serial.println("\nconnected!");
  }
}

void messageReceived(String topic, String payload, char * bytes, unsigned int length) {
  Serial.print(payload);
  //must be present even without subscription
}

void handleHttpRoot() { 
  String chipID = String(ESP.getChipId());
  httpServer.send(200, "text/html", "Hello from esp8266!<br/>chipID: "+ chipID + "<br/>clientId: " + clientId);
}
