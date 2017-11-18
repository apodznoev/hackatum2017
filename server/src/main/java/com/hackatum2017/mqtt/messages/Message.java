package com.hackatum2017.mqtt.messages;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public interface Message {
    Topic getTopic();

    byte[] serialise();
}
