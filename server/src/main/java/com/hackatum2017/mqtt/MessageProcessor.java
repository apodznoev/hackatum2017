package com.hackatum2017.mqtt;

import com.hackatum2017.mqtt.messages.Message;
import com.hackatum2017.mqtt.messages.Topic;

import java.util.function.UnaryOperator;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public interface MessageProcessor {

    void subscribe(Topic topic, UnaryOperator<Message> messageHandler);

    void unsubscribe(Topic topic);
}
