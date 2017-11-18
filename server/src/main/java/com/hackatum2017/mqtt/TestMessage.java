package com.hackatum2017.mqtt;

import com.hackatum2017.mqtt.messages.Message;
import com.hackatum2017.mqtt.messages.Topic;

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
