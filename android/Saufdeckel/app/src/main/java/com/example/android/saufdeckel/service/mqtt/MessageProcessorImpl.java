package com.example.android.saufdeckel.service.mqtt;

import android.util.Log;
import com.example.android.saufdeckel.service.mqtt.messages.Message;
import com.example.android.saufdeckel.service.mqtt.messages.Topic;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class MessageProcessorImpl implements MessageProcessor {
    private final MqttClient client;
    private final Map<Topic, UnaryOperator<Message>> messageHandlers = new ConcurrentHashMap<>();

    public MessageProcessorImpl(MqttClient client) {
        this.client = client;
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String receivedTopic, MqttMessage message) throws Exception {
                Topic topic = Topic.create(receivedTopic);
                if (topic == null) {
                    Log.w(MessageProcessorImpl.class.getSimpleName(), "Received unexpected topic: " + receivedTopic);
                    return;
                }
                if (messageHandlers.containsKey(topic)) {
                    messageHandlers.get(topic).apply(MessageFactory.create(topic, message.getPayload()));
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    @Override
    public void subscribe(Topic topic, UnaryOperator<Message> messageHandler) {
        try {
            messageHandlers.put(topic, messageHandler);
            client.subscribe(topic.getTopic(), 1);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unsubscribe(Topic topic) {
        try {
            messageHandlers.remove(topic);
            client.unsubscribe(topic.getTopic());
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
}
