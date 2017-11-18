package com.example.android.saufdeckel.service.mqtt.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.ByteBuffer;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class WeightChangeMessage implements Message {
    private final int coasterId;
    private final double newWeight;

    @JsonCreator
    public WeightChangeMessage(@JsonProperty("coasterId") int coasterId, @JsonProperty("newWeight") double newWeight) {
        this.coasterId = coasterId;
        this.newWeight = newWeight;
    }

    @Override
    public Topic getTopic() {
        return Topic.WEIGHT_CHANGE_EVENT;
    }


    public int getCoasterId() {
        return coasterId;
    }

    public double getNewWeight() {
        return newWeight;
    }
}
