package com.example.android.saufdeckel.service.mqtt.messages;


import com.example.android.saufdeckel.service.mqtt.messages.Message;
import com.example.android.saufdeckel.service.mqtt.messages.Topic;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class TestMessage implements Message {
    private final byte[] payload;

    public TestMessage(byte[] payload) {
        this.payload = payload;
    }

    @Override
    public Topic getTopic() {
        return Topic.TEST_TOPIC;
    }

    @Override
    public byte[] serialise() {
        return payload;
    }
}
