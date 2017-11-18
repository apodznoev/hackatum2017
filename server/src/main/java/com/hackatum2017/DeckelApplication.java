package com.hackatum2017;

import com.google.common.base.Charsets;
import com.hackatum2017.mqtt.MqttClientFactory;
import com.hackatum2017.mqtt.messages.Topic;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;

@SpringBootApplication
public class DeckelApplication {
    private static final Logger logger = LoggerFactory.getLogger(DeckelApplication.class);

    public static void main(String[] args) throws MqttException, NoSuchAlgorithmException, KeyManagementException,
            KeyStoreException, IOException, CertificateException {
        SpringApplication.run(DeckelApplication.class, args);

        MqttClientFactory factory = new MqttClientFactory();
        MqttClient client = factory.createClent();
        client.setCallback(new MqttCallback() {

            @Override
            public void connectionLost(Throwable cause) { //Called when the client lost the connection to the broker
                logger.error("Lost connection to broker", cause);
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                logger.info(topic + ": " + Arrays.toString(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {//Called when a outgoing publish is complete
                logger.info("Delivery of token {} completed", token);
            }
        });
        client.subscribe(Topic.TEST_TOPIC.getTopic(), 1);
        client.publish(Topic.TEST_TOPIC.getTopic(), "payload".getBytes(Charsets.UTF_8), 1, false);
    }
}


