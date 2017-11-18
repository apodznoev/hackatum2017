package com.hackatum2017.mqtt;

import com.hackatum2017.mqtt.messages.Message;
import com.hackatum2017.mqtt.messages.Topic;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.UnaryOperator;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class MessageProcessorImpl implements MessageProcessor {
    private final static Logger log = LoggerFactory.getLogger(MessageProcessorImpl.class);
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
                if(topic == null) {
                    log.warn("Received unexpected topic: {}", receivedTopic);
                    return;
                }
                if(messageHandlers.containsKey(topic)){
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
