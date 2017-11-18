package com.hackatum2017.mqtt.messages;

import java.nio.ByteBuffer;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class WeightChangeMessage implements Message {
    private final int deckelId;
    private final int newWeight;

    public WeightChangeMessage(byte[] payload) {
        this.deckelId = ByteBuffer.allocateDirect(4).get(payload, 0, 4).getInt();
        this.newWeight = ByteBuffer.allocateDirect(4).get(payload, 4, 8).getInt();
    }

    @Override
    public Topic getTopic() {
        return Topic.WEIGHT_CHANGE_EVENT;
    }

    @Override
    public byte[] serialise() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putInt(0, deckelId);
        byteBuffer.putInt(1, newWeight);
        return byteBuffer.array();
    }

    public int getDeckelId() {
        return deckelId;
    }

    public int getNewWeight() {
        return newWeight;
    }
}
