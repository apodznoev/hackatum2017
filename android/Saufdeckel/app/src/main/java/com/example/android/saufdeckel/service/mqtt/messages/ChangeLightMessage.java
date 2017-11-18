package com.example.android.saufdeckel.service.mqtt.messages;

import java.nio.ByteBuffer;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class ChangeLightMessage implements Message {
    private final int coasterId;
    private final int brightness;

    public ChangeLightMessage(byte[] payload) {
        this.coasterId = ByteBuffer.allocateDirect(4).get(payload, 0, 4).getInt();
        this.brightness = ByteBuffer.allocateDirect(4).get(payload, 4, 8).getInt();
    }

    @Override
    public Topic getTopic() {
        return Topic.BRIGHTNESS_CHANGE_COMMAND;
    }

    @Override
    public byte[] serialise() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putInt(0, coasterId);
        byteBuffer.putInt(1, brightness);
        return byteBuffer.array();
    }

    public int getCoasterId() {
        return coasterId;
    }

    public int getBrightness() {
        return brightness;
    }
}
