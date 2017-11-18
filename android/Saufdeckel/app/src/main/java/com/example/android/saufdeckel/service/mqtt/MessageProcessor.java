package com.example.android.saufdeckel.service.mqtt;


import com.example.android.saufdeckel.service.mqtt.messages.Message;
import com.example.android.saufdeckel.service.mqtt.messages.Topic;


/**
 * @author apodznoev
 * @since 18/11/17
 */
public interface MessageProcessor {

    void subscribe(Topic topic, UnaryOperator<Message> messageHandler);

    void unsubscribe(Topic topic);

}
