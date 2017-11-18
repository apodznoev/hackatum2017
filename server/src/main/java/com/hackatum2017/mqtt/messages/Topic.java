package com.hackatum2017.mqtt.messages;

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
        return Arrays
                .stream(values())
                .filter(topic1 -> topic1.getTopic().equals(receivedTopic))
                .findFirst()
                .orElse(null);
    }
}
