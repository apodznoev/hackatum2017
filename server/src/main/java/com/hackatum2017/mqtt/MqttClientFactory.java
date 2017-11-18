package com.hackatum2017.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class MqttClientFactory {
    private Logger logger = LoggerFactory.getLogger(MqttClientFactory.class);


    public MqttClient createClent() {
        try {
            MqttClient client = new MqttClient(
//                    "ssl://192.168.1.10:18884", //URI
                    "tcp://broker.hivemq.com:1883", //URI
                    MqttClient.generateClientId(), //ClientId
                    new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            /*SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory
                    .getDefaultAlgorithm());
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            options.setSocketFactory(Ssl.getSocketFactory(
                    "broker.cer",
                    "host.cert",
                    "host.key",
                    ""));

            */
            options.setUserName("iteratecIoT");
            options.setPassword("IoT$2017".toCharArray());
            client.connect(options);
            return client;
        } catch (Exception e) {
            logger.error("Cannot create MqttClient due to exception", e);
            throw new RuntimeException(e);
        }
    }
}
