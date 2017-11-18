package com.example.android.saufdeckel.service.mqtt;


import com.example.android.saufdeckel.service.mqtt.messages.Message;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class MessageSenderImpl implements MessageSender {
    private final MqttClient client;

    public MessageSenderImpl(MqttClient client) {
        this.client = client;
    }


    /*@Override
    public CompletableFuture<Void> sendMessage(Message message) {
        CompletableFuture<Void> completableFuture = new CompletableFuture<>();
        try {
            client.publish(message.getTopic().getTopic(), message.serialise(), 1, false);
            completableFuture.complete(null);
        } catch (MqttException e) {
            e.printStackTrace();
            completableFuture.completeExceptionally(e);
        }
        return completableFuture;
    }*/
}
