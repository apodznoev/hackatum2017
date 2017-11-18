package com.example.android.saufdeckel.service.mqtt;

import android.util.Log;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class MqttClientFactory {
    private static final String BROKER_URL = "tcp://broker.hivemq.com:1883";

    public MqttClient createClient() throws MqttException {
            MqttClient client = new MqttClient(
//                    "ssl://192.168.1.10:18884", //URI
                    BROKER_URL, //URI
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
            client.connect(options);
            return client;
    }
}
