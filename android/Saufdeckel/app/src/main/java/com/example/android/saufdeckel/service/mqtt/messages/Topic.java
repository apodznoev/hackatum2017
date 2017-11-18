package com.example.android.saufdeckel.service.mqtt.messages;

import java.util.Arrays;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public enum Topic {
    TEST_TOPIC("iot/iteratec/deckel_test_topic"),
    BRIGHTNESS_CHANGE_COMMAND("iot/iteratec/deckel_change_brightness"),
    WEIGHT_CHANGE_EVENT("iot/iteratec/deckel_weight_changed");

    private final String topic;

    Topic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public static Topic create(String receivedTopic) {
        for(Topic topic : values()){
            if(topic.getTopic().equals(receivedTopic))
                return topic;
        }
        return null;
    }
}
