package com.example.android.saufdeckel.service.mqtt.messages;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public class TestMessage implements Message {
    private final String testParamA;
    private final int testParamB;

    @JsonCreator
    public TestMessage(@JsonProperty("testParamA") String testParamA, @JsonProperty("testParamB") int testParamB) {
        this.testParamA = testParamA;
        this.testParamB = testParamB;
    }


    @Override
    public Topic getTopic() {
        return Topic.TEST_TOPIC;
    }

    @Override
    public String toString() {
        return "TestMessage{" +
                "testParamA='" + testParamA + '\'' +
                ", testParamB=" + testParamB +
                '}';
    }
}
