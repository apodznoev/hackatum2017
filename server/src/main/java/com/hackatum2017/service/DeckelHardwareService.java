package com.hackatum2017.service;

import com.hackatum2017.mqtt.MessageProcessor;
import com.hackatum2017.mqtt.MessageSender;
import com.hackatum2017.mqtt.messages.ChangeLightMessage;
import com.hackatum2017.mqtt.messages.Topic;
import com.hackatum2017.mqtt.messages.WeightChangeMessage;

import java.util.concurrent.CompletableFuture;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class DeckelHardwareService {
    private final MessageSender sender;

    public DeckelHardwareService(MessageProcessor messageProcessor, MessageSender messageSender) {
        messageProcessor.subscribe(Topic.WEIGHT_CHANGE_EVENT, message -> {
            WeightChangeMessage weightChangeMessage = (WeightChangeMessage) message;
            weightChangeMessage.getDeckelId();
            weightChangeMessage.getNewWeight();
            return null;
        });
        sender = messageSender;
    }

    public CompletableFuture<Void> sendLightMessage(ChangeLightMessage changeLightMessage){
        return sender.sendMessage(changeLightMessage);
    }
}
