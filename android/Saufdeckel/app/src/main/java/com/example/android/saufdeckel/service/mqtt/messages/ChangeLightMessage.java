package com.example.android.saufdeckel.service.mqtt.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class ChangeLightMessage implements Message {
    private final int coasterId;
    private final int brightness;

    @JsonCreator
    public ChangeLightMessage(@JsonProperty("coasterId") int coasterId,
                              @JsonProperty("brightness") int brightness) {
        this.coasterId = coasterId;
        this.brightness = brightness;
    }

    @Override
    public Topic getTopic() {
        return Topic.CHANGE_LIGHT_COMMAND;
    }

    public int getCoasterId() {
        return coasterId;
    }

    public int getBrightness() {
        return brightness;
    }
}
