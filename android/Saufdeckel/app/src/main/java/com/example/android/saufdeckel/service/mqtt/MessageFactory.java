package com.example.android.saufdeckel.service.mqtt;


import com.example.android.saufdeckel.service.mqtt.messages.Message;
import com.example.android.saufdeckel.service.mqtt.messages.TestMessage;
import com.example.android.saufdeckel.service.mqtt.messages.Topic;
import com.example.android.saufdeckel.service.mqtt.messages.WeightChangeMessage;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class MessageFactory {
    public static Message create(Topic topic, byte[] payload) {
        switch (topic) {
            case TEST_TOPIC:
                return new TestMessage(payload);
            case WEIGHT_CHANGE_EVENT:
                return new WeightChangeMessage(payload);
            default:
                throw new RuntimeException("Unknown topic:" + topic);
        }
    }
}
